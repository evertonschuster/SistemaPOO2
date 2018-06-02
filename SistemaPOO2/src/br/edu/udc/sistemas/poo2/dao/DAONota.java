package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAONota extends DAO {

	private Nota validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Nota))) {
			throw new Exception("Objeto nao e uma Nota!");
		}
		return (Nota) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Nota nota = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((nota.getId() != null) && (nota.getId() > 0)) {
				sql = "update nota set " +
						" descricao = '" + nota.getDescricao() + "', " + 
						" Datas = '" + nota.getData().toString() + "', " +
						" idFuncionario = '" + ((nota.getFuncionario() != null) ? nota.getFuncionario().getId() : "null") + "', " +
						//" idVeiculo = 'null', " + 
						" numeroDaNota = '" + nota.getNumeroNota() + "' " +
						//" tipoNota = 'null', " +
						//" idContribuinte = 'null' " + 
						" where idnota = " + nota.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into nota (Datas, idFuncionario, idVeiculo, numeroDaNota, tipoNota, idContribuinte,descricao) values(" + 
						"'" + nota.getData().toString() + "', " +
						"'" + ((nota.getFuncionario() != null) ? nota.getFuncionario().getId() : "null") + "', " +
						"null, " +
						"'" + nota.getNumeroNota() + "', " +
						"'" + nota.getTipoNota() + "', " +
						"null, " +
						"'" + nota.getDescricao() + "')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					nota.setId(rst.getInt(1));
				}
			}
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
			if (rst != null) {
				try {
					rst.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	@Override
	public void remove(Integer id) throws Exception {
		Nota nota = new Nota();
		nota.setId(id);
		this.remove(nota);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Nota nota = validate(obj);
		Statement stmt = null;
		try {
			if ((nota.getId() != null) && (nota.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from nota where idnota = " + nota.getId();
				System.out.println(sql);
				stmt.execute(sql);
			}
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	@Override
	public Object[] find(Object obj) throws Exception {
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql = "select idnota, descricao, Datas, idFuncionario, numeroDaNota, tipoNota from nota";

			if (obj != null) {
				Nota nota = validate(obj);

				Boolean bWhere = false;
				if ((nota.getId() != null) && (nota.getId() > 0)) {
					sql = sql + " where idnota = " + nota.getId();
					bWhere = true;
				}

				if ((nota.getDescricao() != null) && (!nota.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + nota.getDescricao().replace(" ", "%") + "%'";
				}
				
				if ((nota.getData() != null) && (!nota.getData().toString().contains("  "))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Datas = '" + nota.getData() + "' ";
				}
				
				if ((nota.getFuncionario() != null) && (nota.getFuncionario().getId() != 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idFuncionario = '" + nota.getFuncionario().getId() + "'";
				}
				
				if ((nota.getNumeroNota() != null) && (nota.getNumeroNota() != 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numeroDaNota = '" + nota.getNumeroNota() + "'";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Nota> list = new Vector<Nota>();
			while (rst.next()) {
				Nota notaResult = new Nota();
				notaResult.setId(rst.getInt("idnota"));
				notaResult.setDescricao(rst.getString("descricao")); //, numeroDaNota, tipoNota
				notaResult.setData(rst.getDate("Datas"));
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rst.getInt("idFuncionario"));
				notaResult.setFuncionario(funcionario);
				
				notaResult.setNumeroNota(rst.getInt("numeroDaNota"));
				notaResult.setTipoNota(rst.getString("tipoNota"));
				list.add(notaResult);
			}

			// Object listResult[] = new Object[list.size()];
			// for (int i = 0; i < listResult.length; i++) {
			// listResult[i] = list.get(i);
			// }
			// return listResult;

			return list.toArray(new Object[list.size()]);
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	@Override
	public Object findByPrimaryKey(Integer id) throws Exception {
		Nota nota = new Nota();
		nota.setId(id);
		return this.findByPrimaryKey(nota);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Nota nota = validate(obj);
		if ((nota.getId() != null) && (nota.getId() > 0)) {
			Nota notaFilter = new Nota();
			notaFilter.setId(nota.getId());

			Object list[] = this.find(notaFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}
