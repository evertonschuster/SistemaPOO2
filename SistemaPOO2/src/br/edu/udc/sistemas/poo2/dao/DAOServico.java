package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAOServico extends DAO {

	private Servico validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Servico))) {
			throw new Exception("Objeto nao e um Servico!");
		}
		return (Servico) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Servico Servico = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((Servico.getId() != null) && (Servico.getId() > 0)) {
				sql = "update Servico set descricao = '" + Servico.getDescricao() + "','" + "valor = " + Servico.getValor() + "' " + "where idServico = " + Servico.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Servico (descricao,valor) " + "values('" + Servico.getDescricao() + "','" + Servico.getValor() + "')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					Servico.setId(rst.getInt(1));
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
		Servico Servico = new Servico();
		Servico.setId(id);
		this.remove(Servico);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Servico Servico = validate(obj);
		Statement stmt = null;
		try {
			if ((Servico.getId() != null) && (Servico.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Servico where idServico = " + Servico.getId();
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
			String sql = "select idServico,descricao from Servico";

			if (obj != null) {
				Servico Servico = validate(obj);

				Boolean bWhere = false;
				if ((Servico.getId() != null) && (Servico.getId() > 0)) {
					sql = sql + " where idServico = " + Servico.getId();
					bWhere = true;
				}

				if ((Servico.getDescricao() != null) && (!Servico.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + Servico.getDescricao().replace(" ", "%") + "%'";
				}
				
				if ((Servico.getDescricao() != null) && (!Servico.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "valor like '%" + Servico.getDescricao().replace(" ", "%") + "%'";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Servico> list = new Vector<Servico>();
			while (rst.next()) {
				Servico ServicoResult = new Servico();
				ServicoResult.setId(rst.getInt("idServico"));
				ServicoResult.setDescricao(rst.getString("descricao"));
				list.add(ServicoResult);
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
		Servico Servico = new Servico();
		Servico.setId(id);
		return this.findByPrimaryKey(Servico);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Servico Servico = validate(obj);
		if ((Servico.getId() != null) && (Servico.getId() > 0)) {
			Servico ServicoFilter = new Servico();
			ServicoFilter.setId(Servico.getId());

			Object list[] = this.find(ServicoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
