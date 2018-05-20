package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAOMarca extends DAO {

	private Marca validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Marca))) {
			throw new Exception("Objeto não é uma Marca!");
		}
		return (Marca) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Marca marca = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((marca.getId() != null) && (marca.getId() > 0)) {
				sql = "update marca set descricao = '" + marca.getDescricao() + "' " + "where idmarca = " + marca.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into marca (descricao) " + "values('" + marca.getDescricao() + "')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					marca.setId(rst.getInt(1));
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
		Marca marca = new Marca();
		marca.setId(id);
		this.remove(marca);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Marca marca = validate(obj);
		Statement stmt = null;
		try {
			if ((marca.getId() != null) && (marca.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from marca where idmarca = " + marca.getId();
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
			String sql = "select idmarca,descricao from marca";

			if (obj != null) {
				Marca marca = validate(obj);

				Boolean bWhere = false;
				if ((marca.getId() != null) && (marca.getId() > 0)) {
					sql = sql + " where idmarca = " + marca.getId();
					bWhere = true;
				}

				if ((marca.getDescricao() != null) && (!marca.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + marca.getDescricao().replace(" ", "%") + "%'";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Marca> list = new Vector<Marca>();
			while (rst.next()) {
				Marca marcaResult = new Marca();
				marcaResult.setId(rst.getInt("idmarca"));
				marcaResult.setDescricao(rst.getString("descricao"));
				list.add(marcaResult);
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
		Marca marca = new Marca();
		marca.setId(id);
		return this.findByPrimaryKey(marca);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Marca marca = validate(obj);
		if ((marca.getId() != null) && (marca.getId() > 0)) {
			Marca marcaFilter = new Marca();
			marcaFilter.setId(marca.getId());

			Object list[] = this.find(marcaFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
