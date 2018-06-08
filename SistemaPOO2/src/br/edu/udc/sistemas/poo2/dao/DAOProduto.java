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
		Produto produto = validate(obj);
		Statement stmt = null; 
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			if ((produto.getId() != null) && (produto.getId() > 0)) {
				sql = "update Produto set descricao = '" + produto.getDescricao() + "'," + "idmarca = " + ((produto.getMarca() != null) ? produto.getMarca().getId() : "null") + ", " + 
				" valor = '" + produto.getValor() + "', qtd = '" + produto.getQtd() + "', qtdMinimo = '" + produto.getQtdMinimo() + "' " +
				" where idProduto = " + produto.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Produto (descricao, idmarca, valor, qtd, qtdMinimo) " +
				" values('" + produto.getDescricao() + "'," + ((produto.getMarca() != null) ? produto.getMarca().getId() : "null") + "," + 
				produto.getValor() + ", " + produto.getQtd() + ", " + produto.getQtdMinimo() + " )";
				
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					produto.setId(rst.getInt(1));
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
		Produto produto = new Produto();
		produto.setId(id);
		this.remove(produto);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Produto produto = validate(obj);
		Statement stmt = null;
		try {
			if ((produto.getId() != null) && (produto.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Produto " + "where idProduto = " + produto.getId();
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
			String sql = "select idProduto, idmarca, descricao, valor, qtd, qtdMinimo from Produto";

			if (obj != null) {
				Produto produto = validate(obj);

				Boolean bWhere = false;
				if ((produto.getId() != null) && (produto.getId() > 0)) {
					sql = sql + " where idProduto = " + produto.getId();
					bWhere = true;
				}

				if ((produto.getDescricao() != null) && (!produto.getDescricao().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "descricao like '%" + produto.getDescricao().replace(" ", "%") + "%'";
				}

				if ((produto.getMarca() != null) && (produto.getMarca().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idmarca = " + produto.getMarca().getId();
				}
				
				if((produto.getValor() != null) && (produto.getValor() > 0)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "valor = " + produto.getValor();
				}
				
				if(produto.getQtd() != null){
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "qtd = " + produto.getQtd();
				}
				
				if(produto.getQtdMinimo() != null) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "qtdMinimo = " + produto.getQtdMinimo();
				}
				
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Produto> list = new Vector<Produto>();
			while (rst.next()) {
				Produto produtoResult = new Produto();
				produtoResult.setId(rst.getInt("idProduto"));
				produtoResult.setDescricao(rst.getString("descricao"));
				
				produtoResult.setValor(rst.getDouble("valor"));
				produtoResult.setQtd(rst.getInt("qtd"));
				produtoResult.setQtdMinimo(rst.getInt("qtdMinimo"));

				Marca marca = new Marca();
				marca.setId(rst.getInt("idmarca"));
				produtoResult.setMarca(marca);

				list.add(produtoResult);
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
		Produto produto = new Produto();
		produto.setId(id);
		return this.findByPrimaryKey(produto);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Produto produto = validate(obj);
		if ((produto.getId() != null) && (produto.getId() > 0)) {
			Produto produtoFilter = new Produto();
			produtoFilter.setId(produto.getId());

			Object list[] = this.find(produtoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
