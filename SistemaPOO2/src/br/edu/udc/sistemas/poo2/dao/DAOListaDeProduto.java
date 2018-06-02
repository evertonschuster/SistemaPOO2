package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;

public class DAOListaDeProduto extends DAO {

	private ListaDeProduto validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof ListaDeProduto))) {
			throw new Exception("Objeto nao e uma ListaDeProduto!");
		}
		return (ListaDeProduto) obj;
	}

	protected void attEstoque(int qntVendida, int qntAnterior, Produto produto, String tipo) throws Exception {
		if(( qntVendida != 0) && !tipo.isEmpty()) {
			qntVendida = qntVendida - qntAnterior;
			
			DAOProduto daoProduto = new DAOProduto();
			if(tipo.equalsIgnoreCase("compra")) {
				produto = (Produto) daoProduto.findByPrimaryKey(produto);
				if(produto == null) {
					throw new ExceptionValidacao("Produto nao atualizado");
				}
				produto.setQtd(produto.getQtd() + qntVendida);
				
			}else if(tipo.equalsIgnoreCase("venda")) {
				produto = (Produto) daoProduto.findByPrimaryKey(produto);
				if(produto == null) {
					throw new ExceptionValidacao("Produto nao atualizado");
				}
				produto.setQtd(produto.getQtd() - qntVendida);
			}else {
				return;
			}
			
			daoProduto.save(produto);
		}
	}
	@Override
	public void save(Object obj) throws Exception {
		ListaDeProduto listaDeProduto = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((listaDeProduto.getNota() != null) && (listaDeProduto.getProduto() != null) &&
					(listaDeProduto.getNota().getId() > 0) && (listaDeProduto.getProduto().getId() > 0) ) {
				
				ListaDeProduto listaDeProdutoFind = (ListaDeProduto) this.findByPrimaryKey(listaDeProduto);
				if(listaDeProdutoFind != null){
					sql = "update listaDeProdutos set " +
							" idNota = '" + listaDeProduto.getNota().getId() + "', " +
							" idProduto = '" + listaDeProduto.getProduto().getId() + "', " +
							" qnt = '" + listaDeProduto.getQnt() + "' " +
								" where idNota = " + listaDeProduto.getNota().getId() +
								" AND idProduto = " + listaDeProduto.getProduto().getId();
					attEstoque(listaDeProduto.getQnt(), listaDeProdutoFind.getQnt(), listaDeProduto.getProduto(), listaDeProduto.getNota().getTipoNota());
				}else {
					sql = "insert into listaDeProdutos (idProduto, idNota, qnt) " + 
							"values('" + listaDeProduto.getProduto().getId() + "', " +
							" '" + listaDeProduto.getNota().getId() + "', " +
							" '" + listaDeProduto.getQnt()  +"')";
					attEstoque(listaDeProduto.getQnt(), 0, listaDeProduto.getProduto(), listaDeProduto.getNota().getTipoNota());
				}
				
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into listaDeProdutos (idProduto, idNota, qnt) " + 
						"values('" + listaDeProduto.getProduto().getId() + "', " +
						" '" + listaDeProduto.getNota().getId() + "', " +
						" '" + listaDeProduto.getQnt()  +"')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					listaDeProduto.setId(rst.getInt(1));
				}
				attEstoque(listaDeProduto.getQnt(), 0, listaDeProduto.getProduto(), listaDeProduto.getNota().getTipoNota());
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
		throw new ExceptionValidacao("Remocao apenas por chave composta");
	}

	@Override
	public void remove(Object obj) throws Exception {
		ListaDeProduto listaDeProduto = validate(obj);
		Statement stmt = null;
		try {
			String sql;
			stmt = Database.getInstance().getConnection().createStatement();
			if ((listaDeProduto.getNota() != null) && (listaDeProduto.getProduto() != null) &&
					(listaDeProduto.getNota().getId() != null) && (listaDeProduto.getProduto().getId() != null)  &&
					(listaDeProduto.getNota().getId() > 0) && (listaDeProduto.getProduto().getId() > 0) ) {
				stmt = Database.getInstance().getConnection().createStatement();
				sql = "delete from listaDeProdutos " +
						" where idNota = '" + listaDeProduto.getNota().getId() + "' "+
						" AND idProduto = '" + listaDeProduto.getProduto().getId() + "'";
				
				attEstoque(listaDeProduto.getQnt(), 0, listaDeProduto.getProduto(),
						(listaDeProduto.getNota().getTipoNota().equalsIgnoreCase("compra") ? "venda" : "compra")  );
				
			}else if ((listaDeProduto.getNota() != null) && (listaDeProduto.getProduto() == null) &&
					(listaDeProduto.getNota().getId() != null )  && (listaDeProduto.getNota().getId() > 0)  ) {
				
				DAONota daoNota = new DAONota();
				listaDeProduto.setNota((Nota) daoNota.findByPrimaryKey(listaDeProduto.getNota()));
				Object[] listafin = find(listaDeProduto);
				if(listafin != null) {
					for(Object objLista : listafin) {
						ListaDeProduto listaDell = (ListaDeProduto) objLista;
						attEstoque(listaDell.getQnt(), 0, listaDell.getProduto(),
								(listaDeProduto.getNota().getTipoNota().equalsIgnoreCase("compra") ? "venda" : "compra")  );
					}
				}
				
				sql = "delete from listaDeProdutos " +
						" where idNota = '" + listaDeProduto.getNota().getId() + "' ";							
			}else {
				sql = "";
			}
			
			System.out.println(sql);
			stmt.execute(sql);
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
			String sql = "select idProduto, idNota, qnt from listaDeProdutos";

			if (obj != null) {
				ListaDeProduto listaDeProduto = validate(obj);

				Boolean bWhere = false;
//				if ((listaDeProduto.getId() != null) && (listaDeProduto.getId() > 0)) {
//					sql = sql + " where idlistaDeProduto = " + listaDeProduto.getId();
//					bWhere = true;
//				}

				if((listaDeProduto.getNota() != null) && (listaDeProduto.getNota().getId() != null) ) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idNota = '" + listaDeProduto.getNota().getId() + "' ";
				}
				
				if ( (listaDeProduto.getProduto() != null) && (listaDeProduto.getProduto().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idProduto = '" + listaDeProduto.getProduto().getId() + "' ";
				}
				
				if ( (listaDeProduto != null) && (listaDeProduto.getQnt() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "qnt = '" + listaDeProduto.getQnt() + "' ";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<ListaDeProduto> list = new Vector<ListaDeProduto>();
			while (rst.next()) {
				ListaDeProduto listaDeProdutoResult = new ListaDeProduto();

				Produto produto = new Produto();
				produto.setId(rst.getInt("idProduto"));
				listaDeProdutoResult.setProduto(produto);
				
				Nota nota = new Nota();
				nota.setId(rst.getInt("idNota"));
				listaDeProdutoResult.setNota(nota);
				
				listaDeProdutoResult.setQnt(rst.getInt("qnt"));
				
				list.add(listaDeProdutoResult);
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
		throw new ExceptionValidacao("Find por id composta");
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		ListaDeProduto listaDeProduto = validate(obj);
		if ((listaDeProduto.getNota() != null) && (listaDeProduto.getProduto() != null) &&
				(listaDeProduto.getNota().getId() > 0) && (listaDeProduto.getProduto().getId() > 0) ) {
			ListaDeProduto listaDeProdutoFilter = new ListaDeProduto();
			listaDeProdutoFilter.setNota(listaDeProduto.getNota());
			listaDeProdutoFilter.setProduto(listaDeProduto.getProduto());

			Object list[] = this.find(listaDeProdutoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}
