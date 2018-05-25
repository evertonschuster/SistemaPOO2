package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAOCliente extends DAO {

	private Cliente validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Cliente))) {
			throw new Exception("Objeto nao e um Cliente!");
		}
		return (Cliente) obj;
	}
	

	@Override
	public void save(Object obj) throws Exception {
		Cliente Cliente = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			if ((Cliente.getId() != null) && (Cliente.getId() > 0)) {
				sql = "update Cliente set nome = '" + Cliente.getNome() + "'," + "rg = '" + Cliente.getRG() + "'," + "cpf = '" + Cliente.getCPF() + "'," + "dtnasc = '" + Cliente.getDtNasc() + "'," + "telefone = '" + Cliente.getTelf() + "'," + "celular = '" + Cliente.getCelular() + "'," + "logradouro = '" + Cliente.getLogradoudo() + "'," + "numero = '" + Cliente.getNumero() + "'," + "bairro = '" + Cliente.getBairro() + "'," + "cidade = '" + Cliente.getCidade() + "'," + "estado = '" + Cliente.getEstado() + "'," + "cep = '" + Cliente.getCep() + "' "  + "where idCliente = " + Cliente.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Cliente (nome,rg,cpf,telefone,celular,logradouro,numero,bairro,cidade,estado,cep) " + "values('" + Cliente.getNome() + Cliente.getRG() + Cliente.getCPF() + Cliente.getDtNasc() + Cliente.getTelf()  + Cliente.getCelular()  + Cliente.getLogradoudo()  + Cliente.getNumero()  + Cliente.getBairro()  + Cliente.getCidade()   + Cliente.getEstado()  + Cliente.getCep()  + "')";
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					Cliente.setId(rst.getInt(1));
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
		Cliente Cliente = new Cliente();
		Cliente.setId(id);
		this.remove(Cliente);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Cliente Cliente = validate(obj);
		Statement stmt = null;
		try {
			if ((Cliente.getId() != null) && (Cliente.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Cliente " + "where idCliente = " + Cliente.getId();
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
			String sql = "select idCliente,nome,rg,cpf,dtnasc,telefone,celular,logradouro,numero,bairro,cidade,estado,cep from Cliente";

			if (obj != null) {
				Cliente Cliente = validate(obj);

				Boolean bWhere = false;
				if ((Cliente.getId() != null) && (Cliente.getId() > 0)) {
					sql = sql + " where idCliente = " + Cliente.getId();
					bWhere = true;
				}

				if ((Cliente.getNome() != null) && (!Cliente.getNome().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "nome like '%" + Cliente.getNome().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getRG() != null) && (!Cliente.getRG().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "rg like '%" + Cliente.getRG().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getCPF() != null) && (!Cliente.getCPF().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cpf like '%" + Cliente.getCPF().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getDtNasc() != null) && (!Cliente.getDtNasc().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "dtnasc like '%" + Cliente.getDtNasc().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getTelf() != null) && (!Cliente.getTelf().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "telf like '%" + Cliente.getTelf().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getCelular() != null) && (!Cliente.getCelular().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "celular like '%" + Cliente.getCelular().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getLogradoudo() != null) && (!Cliente.getLogradoudo().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Logradouro like '%" + Cliente.getLogradoudo().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getNumero() != null) && (!Cliente.getNumero().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numero like '%" + Cliente.getNumero().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getBairro() != null) && (!Cliente.getBairro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "bairro like '%" + Cliente.getBairro().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getCidade() != null) && (!Cliente.getCidade().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cidade like '%" + Cliente.getCidade().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getEstado() != null) && (!Cliente.getEstado().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Estado like '%" + Cliente.getEstado().replace(" ", "%") + "%'";
				}
				
				if ((Cliente.getCep() != null) && (!Cliente.getCep().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cep like '%" + Cliente.getCep().replace(" ", "%") + "%'";
				}
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Cliente> list = new Vector<Cliente>();
			while (rst.next()) {
				Cliente ClienteResult = new Cliente();
				ClienteResult.setId(rst.getInt("idCliente"));
				ClienteResult.setNome(rst.getString("nome"));
				ClienteResult.setRG(rst.getString("rg"));
				ClienteResult.setCPF(rst.getString("cpf"));
				ClienteResult.setDtNasc(rst.getString("dtnasc"));
				ClienteResult.setTelf(rst.getString("telefone"));
				ClienteResult.setCelular(rst.getString("celular"));
				ClienteResult.setLogradoudo(rst.getString("logradouro"));
				ClienteResult.setNumero(rst.getString("numero"));
				ClienteResult.setBairro(rst.getString("bairro"));
				ClienteResult.setCidade(rst.getString("cidade"));
				ClienteResult.setEstado(rst.getString("estado"));
				ClienteResult.setCep(rst.getString("cep"));
				list.add(ClienteResult);
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
		Cliente Cliente = new Cliente();
		Cliente.setId(id);
		return this.findByPrimaryKey(Cliente);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Cliente Cliente = validate(obj);
		if ((Cliente.getId() != null) && (Cliente.getId() > 0)) {
			Cliente ClienteFilter = new Cliente();
			ClienteFilter.setId(Cliente.getId());

			Object list[] = this.find(ClienteFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
