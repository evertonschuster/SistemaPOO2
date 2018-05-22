package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;



public class DAOProduto extends DAO {

	private Produto validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Produto))) {
			throw new Exception("Objeto não é uma Produto!");
		}
		return (Produto) obj;
	}
	
	

	@Override
	public void save(Object obj) throws Exception {
		Produto Produto = validate(obj);
		Statement stmt = null; 
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			if ((Produto.getId() != null) && (Produto.getId() > 0)) {
				sql = "update Produto set descricao = '" + Produto.getDescricao() + "'," + "idmarca = " + ((Produto.getMarca() != null) ? Produto.getMarca().getId() : "null") + " " + "where idProduto = " + Produto.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Produto (descricao,idmarca) " + "values('" + Produto.getDescricao() + "'," + ((Produto.getMarca() != null) ? Produto.getMarca().getId() : "null") + ")";
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					Produto.setId(rst.getInt(1));
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
		Produto Produto = new Produto();
		Produto.setId(id);
		this.remove(Produto);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Produto Produto = validate(obj);
		Statement stmt = null;
		try {
			if ((Produto.getId() != null) && (Produto.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Produto " + "where idProduto = " + Produto.getId();
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
			String sql = "select idProduto,idmarca,descricao from Produto";

			if (obj != null) {
				Produto Produto = validate(obj);

				Boolean bWhere = false;
				if ((Produto.getId() != null) && (Produto.getId() > 0)) {
					sql = sql + " where idProduto = " + Produto.getId();
					bWhere = true;
				}

				if ((Produto.getDescricao() != null) && (!Produto.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + Produto.getDescricao().replace(" ", "%") + "%'";
				}

				if ((Produto.getMarca() != null) && (Produto.getMarca().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idmarca = " + Produto.getMarca().getId();
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Produto> list = new Vector<Produto>();
			while (rst.next()) {
				Produto ProdutoResult = new Produto();
				ProdutoResult.setId(rst.getInt("idProduto"));
				ProdutoResult.setDescricao(rst.getString("descricao"));

				Marca marca = new Marca();
				marca.setId(rst.getInt("idmarca"));
				ProdutoResult.setMarca(marca);

				list.add(ProdutoResult);
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
		Produto Produto = new Produto();
		Produto.setId(id);
		return this.findByPrimaryKey(Produto);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Produto Produto = validate(obj);
		if ((Produto.getId() != null) && (Produto.getId() > 0)) {
			Produto ProdutoFilter = new Produto();
			ProdutoFilter.setId(Produto.getId());

			Object list[] = this.find(ProdutoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
