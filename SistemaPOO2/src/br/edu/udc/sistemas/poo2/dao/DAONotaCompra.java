package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.NotaCompra;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAONotaCompra extends DAONota {

	private NotaCompra validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof NotaCompra))) {
			throw new Exception("Objeto nao e uma NotaCompra!");
		}
		return (NotaCompra) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		NotaCompra notaCompra = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((notaCompra.getId() != null) && (notaCompra.getId() > 0)) {
				sql = "update nota set " +
						" descricao = '" + notaCompra.getDescricao() + "', " + 
						" Datas = '" + notaCompra.getData().toString() + "', " +
						" idFuncionario = '" + ((notaCompra.getFuncionario() != null) ? notaCompra.getFuncionario().getId() : "null") + "', " +
						//" idVeiculo = 'null', " + 
						" numeroDaNota = '" + notaCompra.getNumeroNota() + "', " +
						//" tipoNotaCompra = 'null', " +
						" idContribuinte = '" + notaCompra.getFornecedor().getId() + "' " + 
						" where idnota = " + notaCompra.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into nota (Datas, idFuncionario, idVeiculo, numeroDaNota, tipoNota, idContribuinte,descricao) values(" + 
						"'" + notaCompra.getData().toString() + "', " +
						"'" + ((notaCompra.getFuncionario() != null) ? notaCompra.getFuncionario().getId() : "null") + "', " +
						"null, " +
						"'" + notaCompra.getNumeroNota() + "', " +
						"'" + notaCompra.getTipoNota() + "', " +
						"'" + notaCompra.getFornecedor().getId() + "', " +
						"'" + notaCompra.getDescricao() + "')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					notaCompra.setId(rst.getInt(1));
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
		NotaCompra notaCompra = new NotaCompra();
		notaCompra.setId(id);
		this.remove(notaCompra);
	}

	@Override
	public void remove(Object obj) throws Exception {
		NotaCompra notaCompra = validate(obj);
		Statement stmt = null;
		try {
			if ((notaCompra.getId() != null) && (notaCompra.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from nota where idnota = " + notaCompra.getId();
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
			String sql = "select idnota, descricao, Datas, idFuncionario, numeroDaNota, tipoNota, idContribuinte from nota";

			if (obj != null) {
				NotaCompra notaCompra = validate(obj);

				Boolean bWhere = false;
				if ((notaCompra.getId() != null) && (notaCompra.getId() > 0)) {
					sql = sql + " where idnota = " + notaCompra.getId();
					bWhere = true;
				}

				if ((notaCompra.getDescricao() != null) && (!notaCompra.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + notaCompra.getDescricao().replace(" ", "%") + "%'";
				}
				
				if ((notaCompra.getData() != null) && (!notaCompra.getData().toString().contains("  "))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Datas = '" + notaCompra.getData() + "' ";
				}
				
				if ((notaCompra.getFuncionario() != null) && (notaCompra.getFuncionario().getId() != 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idFuncionario = '" + notaCompra.getFuncionario().getId() + "'";
				}
				
				if ((notaCompra.getNumeroNota() != null) && (notaCompra.getNumeroNota() != 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numeroDaNota = '" + notaCompra.getNumeroNota() + "'";
				}
				
				if ((notaCompra.getFornecedor() != null) && (notaCompra.getFornecedor().getId() != 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idContribuinte = '" + notaCompra.getFornecedor().getId() + "'";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<NotaCompra> list = new Vector<NotaCompra>();
			while (rst.next()) {
				NotaCompra notaCompraResult = new NotaCompra();
				notaCompraResult.setId(rst.getInt("idnota"));
				notaCompraResult.setDescricao(rst.getString("descricao")); //, numeroDaNotaCompra, tipoNotaCompra
				notaCompraResult.setData(rst.getDate("Datas"));
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rst.getInt("idFuncionario"));
				notaCompraResult.setFuncionario(funcionario);
				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rst.getInt("idContribuinte"));
				notaCompraResult.setFornecedor(fornecedor);
				
				notaCompraResult.setNumeroNota(rst.getInt("numeroDaNota"));
				notaCompraResult.setTipoNota(rst.getString("tipoNota"));
				list.add(notaCompraResult);
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
		NotaCompra notaCompra = new NotaCompra();
		notaCompra.setId(id);
		return this.findByPrimaryKey(notaCompra);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		NotaCompra notaCompra = validate(obj);
		if ((notaCompra.getId() != null) && (notaCompra.getId() > 0)) {
			NotaCompra notaCompraFilter = new NotaCompra();
			notaCompraFilter.setId(notaCompra.getId());

			Object list[] = this.find(notaCompraFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}
