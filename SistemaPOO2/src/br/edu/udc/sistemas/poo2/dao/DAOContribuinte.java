package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Contribuinte;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAOContribuinte extends DAO {

	private Contribuinte validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Contribuinte))) {
			throw new Exception("Objeto nao e uma Contribuinte!");
		}
		return (Contribuinte) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Contribuinte contribuinte = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((contribuinte.getId() != null) && (contribuinte.getId() > 0)) {
				sql = "update contribuinte set "
						
						+ " dtNasc = '" + contribuinte.getDataNascimento()+ "', " 
						+ "telf = '" 	+ contribuinte.getTelefone() + "' , "
						+ "celular = '" + contribuinte.getCelular() + "', "
						+ "logradouro = '" + contribuinte.getLogradouro() + "', "
						+ "numero = '"	+ contribuinte.getNumero() + "', "
						+ "bairro = '" 	+ contribuinte.getBairro() + "', "
						+ "cidade = '" 	+ contribuinte.getCidade() + "', "
						+ "estado = '" 	+ contribuinte.getEstado() + "', "
						+ "cep = '" 	+ contribuinte.getCep() + "' "

						+ "where idcontribuinte = " + contribuinte.getId();
				System.out.println(sql);
				stmt.execute(sql);
			
			} else {
				sql = "insert into contribuinte (dtNasc, telf, celular, logradouro, numero, bairro, cidade, estado, cep)" 		
						+ "values('" + contribuinte.getDataNascimento() + "', '"
						+ contribuinte.getTelefone() + "', '"
						+ contribuinte.getCelular() + "', '"
						+ contribuinte.getLogradouro()+ "', '"
						+ contribuinte.getNumero() + "', '"
						+ contribuinte.getBairro() + "', '"
						+ contribuinte.getCidade() + "', '"
						+ contribuinte.getEstado() + "', '"
						+ contribuinte.getCep() + "')";
				
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					contribuinte.setId(rst.getInt(1));
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
		Contribuinte contribuinte = new Contribuinte();
		contribuinte.setId(id);
		this.remove(contribuinte);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Contribuinte contribuinte = validate(obj);
		Statement stmt = null;
		try {
			if ((contribuinte.getId() != null) && (contribuinte.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from contribuinte where idcontribuinte = " + contribuinte.getId();
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
			String sql = "select idcontribuinte,descricao from contribuinte";

			if (obj != null) {
				Contribuinte contribuinte = validate(obj);

				Boolean bWhere = false;
				if ((contribuinte.getId() != null) && (contribuinte.getId() > 0)) {
					sql = sql + " where idcontribuinte = " + contribuinte.getId();
					bWhere = true;
				}

				if (contribuinte.getDataNascimento() != null) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "dtNasc = '" + contribuinte.getDataNascimento() + "' ";
				}
				
				if ((contribuinte.getTelefone() != null) && (!contribuinte.getTelefone().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "telf like '%" + contribuinte.getTelefone().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getCelular() != null) && (!contribuinte.getCelular().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "celular like '%" + contribuinte.getCelular().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getLogradouro() != null) && (!contribuinte.getLogradouro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "logradouro like '%" + contribuinte.getLogradouro().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getNumero() != null) && (!contribuinte.getNumero().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numero like '%" + contribuinte.getNumero().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getBairro() != null) && (!contribuinte.getBairro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "bairro like '%" + contribuinte.getBairro().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getCidade() != null) && (!contribuinte.getCidade().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cidade like '%" + contribuinte.getCidade().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getEstado() != null) && (!contribuinte.getEstado().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "estado like '%" + contribuinte.getEstado().replace(" ", "%") + "%'";
				}
				
				if ((contribuinte.getCep() != null) && (!contribuinte.getCep().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cep like '%" + contribuinte.getCep().replace(" ", "%") + "%'";
				}
			
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Contribuinte> list = new Vector<Contribuinte>();
			while (rst.next()) {
				Contribuinte contribuinteResult = new Contribuinte();
				contribuinteResult.setId		(rst.getInt("idcontribuinte"));
				contribuinteResult.setDataNascimento(rst.getDate("dtNasc"));
				contribuinteResult.setTelefone	(rst.getString("telf"));
				contribuinteResult.setCelular	(rst.getString("celular"));
				contribuinteResult.setLogradouro(rst.getString("logradouro"));
				contribuinteResult.setNumero	(rst.getString("numero"));
				contribuinteResult.setBairro	(rst.getString("bairro"));
				contribuinteResult.setCidade	(rst.getString("cidade"));
				contribuinteResult.setEstado	(rst.getString("estado"));
				contribuinteResult.setCep		(rst.getString("cep"));
				list.add(contribuinteResult);
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
		Contribuinte contribuinte = new Contribuinte();
		contribuinte.setId(id);
		return this.findByPrimaryKey(contribuinte);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Contribuinte contribuinte = validate(obj);
		if ((contribuinte.getId() != null) && (contribuinte.getId() > 0)) {
			Contribuinte contribuinteFilter = new Contribuinte();
			contribuinteFilter.setId(contribuinte.getId());

			Object list[] = this.find(contribuinteFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
