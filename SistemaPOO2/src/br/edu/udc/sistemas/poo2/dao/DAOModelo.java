package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAOModelo extends DAO {

	private Modelo validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Modelo))) {
			throw new Exception("Objeto não é uma Modelo!");
		}
		return (Modelo) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Modelo modelo = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			if ((modelo.getId() != null) && (modelo.getId() > 0)) {
				sql = "update modelo set descricao = '" + modelo.getDescricao() + "'," + "idmarca = " + ((modelo.getMarca() != null) ? modelo.getMarca().getId() : "null") + " " + "where idmodelo = " + modelo.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into modelo (descricao,idmarca) " + "values('" + modelo.getDescricao() + "'," + ((modelo.getMarca() != null) ? modelo.getMarca().getId() : "null") + ")";
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					modelo.setId(rst.getInt(1));
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
		Modelo modelo = new Modelo();
		modelo.setId(id);
		this.remove(modelo);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Modelo modelo = validate(obj);
		Statement stmt = null;
		try {
			if ((modelo.getId() != null) && (modelo.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from modelo " + "where idmodelo = " + modelo.getId();
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
			String sql = "select idmodelo,idmarca,descricao from modelo";

			if (obj != null) {
				Modelo modelo = validate(obj);

				Boolean bWhere = false;
				if ((modelo.getId() != null) && (modelo.getId() > 0)) {
					sql = sql + " where idmodelo = " + modelo.getId();
					bWhere = true;
				}

				if ((modelo.getDescricao() != null) && (!modelo.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + modelo.getDescricao().replace(" ", "%") + "%'";
				}

				if ((modelo.getMarca() != null) && (modelo.getMarca().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idmarca = " + modelo.getMarca().getId();
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Modelo> list = new Vector<Modelo>();
			while (rst.next()) {
				Modelo modeloResult = new Modelo();
				modeloResult.setId(rst.getInt("idmodelo"));
				modeloResult.setDescricao(rst.getString("descricao"));

				Marca marca = new Marca();
				marca.setId(rst.getInt("idmarca"));
				modeloResult.setMarca(marca);

				list.add(modeloResult);
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
		Modelo modelo = new Modelo();
		modelo.setId(id);
		return this.findByPrimaryKey(modelo);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Modelo modelo = validate(obj);
		if ((modelo.getId() != null) && (modelo.getId() > 0)) {
			Modelo modeloFilter = new Modelo();
			modeloFilter.setId(modelo.getId());

			Object list[] = this.find(modeloFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
