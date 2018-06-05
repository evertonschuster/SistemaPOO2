package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.ListaDeServico;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;

public class DAOListaDeServico extends DAO {

	private ListaDeServico validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof ListaDeServico))) {
			throw new Exception("Objeto nao e uma ListaDeServico!");
		}
		return (ListaDeServico) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		ListaDeServico listaDeServico = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((listaDeServico.getNota() != null) && (listaDeServico.getServico() != null) &&
					(listaDeServico.getNota().getId() > 0) && (listaDeServico.getServico().getId() > 0) ) {
				
				ListaDeServico listaDeServicoFind = (ListaDeServico) this.findByPrimaryKey(listaDeServico);
				if(listaDeServicoFind != null){
					sql = "update listaDeServicos set " +
							" idNota = '" + listaDeServico.getNota().getId() + "', " +
							" idServico = '" + listaDeServico.getServico().getId() + "' " +

								" where idNota = " + listaDeServico.getNota().getId() +
								" AND idServico = " + listaDeServico.getServico().getId();
				}else {
					sql = "insert into listaDeServicos (idServico, idNota) " + 
							"values('" + listaDeServico.getServico().getId() + "', " +
							" '" + listaDeServico.getNota().getId() + "')";
				}
				
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into listaDeServicos (idServico, idNota) " + 
						"values('" + listaDeServico.getServico().getId() + "', " +
						" '" + listaDeServico.getNota().getId()  +"')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					listaDeServico.setId(rst.getInt(1));
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
		throw new ExceptionValidacao("Remocao apenas por chave composta");
	}

	@Override
	public void remove(Object obj) throws Exception {
		ListaDeServico listaDeServico = validate(obj);
		Statement stmt = null;
		try {
			String sql;
			stmt = Database.getInstance().getConnection().createStatement();
			if ((listaDeServico.getNota() != null) && (listaDeServico.getServico() != null) &&
					(listaDeServico.getNota().getId() != null) && (listaDeServico.getServico().getId() != null)  &&
					(listaDeServico.getNota().getId() > 0) && (listaDeServico.getServico().getId() > 0) ) {
				stmt = Database.getInstance().getConnection().createStatement();
				sql = "delete from listaDeServicos " +
						" where idNota = '" + listaDeServico.getNota().getId() + "' "+
						" AND idServico = '" + listaDeServico.getServico().getId() + "'";
				
			
				
			}else if ((listaDeServico.getNota() != null) && (listaDeServico.getServico() == null) &&
					(listaDeServico.getNota().getId() != null )  && (listaDeServico.getNota().getId() > 0)  ) {
				
				
				
				sql = "delete from listaDeServicos " +
						" where idNota = '" + listaDeServico.getNota().getId() + "' ";							
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
			String sql = "select idServico, idNota from listaDeServicos";

			if (obj != null) {
				ListaDeServico listaDeServico = validate(obj);

				Boolean bWhere = false;
//				if ((listaDeServico.getId() != null) && (listaDeServico.getId() > 0)) {
//					sql = sql + " where idlistaDeServico = " + listaDeServico.getId();
//					bWhere = true;
//				}

				if((listaDeServico.getNota() != null) && (listaDeServico.getNota().getId() != null) ) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idNota = '" + listaDeServico.getNota().getId() + "' ";
				}
				
				if ( (listaDeServico.getServico() != null) && (listaDeServico.getServico().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idServico = '" + listaDeServico.getServico().getId() + "' ";
				}
				
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<ListaDeServico> list = new Vector<ListaDeServico>();
			while (rst.next()) {
				ListaDeServico listaDeServicoResult = new ListaDeServico();

				Servico servico = new Servico();
				servico.setId(rst.getInt("idServico"));
				listaDeServicoResult.setServico(servico);
				
				Nota nota = new Nota();
				nota.setId(rst.getInt("idNota"));
				listaDeServicoResult.setNota(nota);
				
				
				list.add(listaDeServicoResult);
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
		ListaDeServico listaDeServico = validate(obj);
		if ((listaDeServico.getNota() != null) && (listaDeServico.getServico() != null) &&
				(listaDeServico.getNota().getId() > 0) && (listaDeServico.getServico().getId() > 0) ) {
			ListaDeServico listaDeServicoFilter = new ListaDeServico();
			listaDeServicoFilter.setNota(listaDeServico.getNota());
			listaDeServicoFilter.setServico(listaDeServico.getServico());

			Object list[] = this.find(listaDeServicoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}
