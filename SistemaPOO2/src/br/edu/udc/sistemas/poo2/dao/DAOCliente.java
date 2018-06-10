package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.postgresql.util.PSQLException;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;

public class DAOCliente extends DAOContribuinte {

	private Cliente validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Cliente))) {
			throw new ExceptionValidacao("Objeto nao e um Cliente!");
		}
		return (Cliente) obj;
	}
	

	@Override
	public void save(Object obj) throws Exception {
		Cliente cliente = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			
			if ((cliente.getId() != null) && (cliente.getId() > 0)) {
				super.save(obj);
				sql = "update Cliente set " 
						+ "nome = '" + cliente.getNome() +"' " 
						+ ", rg = '" + cliente.getRG()  +"' " 
						+ ", cpf = '" + cliente.getCPF()  +"' " 
						+ " where idCliente = " + cliente.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				
				Cliente clienteFind = new Cliente();
				clienteFind.setCPF(cliente.getCPF());
				if( !(this.find(clienteFind).length == 0)) {
					throw new ExceptionValidacao("Cliente/CPF ja Cadastrado!");
				}
				
				
				super.save(obj);
				sql = "insert into Cliente (idCliente, nome, rg, cpf) " 
						+ " values('" + cliente.getId() + "', '" +  cliente.getNome() + "', '" + cliente.getRG()  + "', '" +  cliente.getCPF() + "')";
				
				
				System.out.println(sql);
				stmt.execute(sql);
				/*stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					cliente.setId(rst.getInt(1));
				}*/
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
		Cliente cliente = new Cliente();
		cliente.setId(id);
		this.remove(cliente);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Cliente cliente = validate(obj);
		Statement stmt = null;
		try {
			if ((cliente.getId() != null) && (cliente.getId() > 0)) {
				
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Cliente " + "where idCliente = " + cliente.getId();
				System.out.println(sql);
				stmt.execute(sql);
				super.remove(obj);
			}
		} catch (PSQLException sql) { //violates foreign key
			if(sql.getMessage().contains("violates foreign key")){
				try {
					this.rollback();
				} catch (Exception e2) {
				}
				throw new ExceptionValidacao("Possui Funcionarios vinculados com este registro!");
			}
			
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw sql;
		
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
			String sql = "select idCliente,nome,rg,cpf,dtnasc,telf,celular,logradouro,numero,bairro,cidade,estado,cep " +
					" from Cliente " + 
					" INNER JOIN Contribuinte ON Contribuinte.idContribuinte = Cliente.idCliente";


			if (obj != null) {
				Cliente cliente = validate(obj);

				Boolean bWhere = false;
				if ((cliente.getId() != null) && (cliente.getId() > 0)) {
					sql = sql + " where idCliente = " + cliente.getId();
					bWhere = true;
				}

				if ((cliente.getNome() != null) && (!cliente.getNome().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "nome like '%" + cliente.getNome().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getRG() != null) && (!cliente.getRG().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "rg like '%" + cliente.getRG().replaceAll("[. ]", "%").replaceAll("[-]", "%") + "%'";
				}
				
				if ((cliente.getCPF() != null) && (!cliente.getCPF().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cpf like '%" + cliente.getCPF().replaceAll("[. ]", "%").replaceAll("[-]", "%") + "%'";
				}
				
				if(cliente.getDataNascimento() != null) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "dtnasc = '" + cliente.getDataNascimento().toString() + "' ";
				}
				
				if ((cliente.getTelefone() != null) && (!cliente.getTelefone().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "telf like '%" + cliente.getTelefone().replaceAll("[- ]", "%").replaceAll("[()]", "%") + "%'";
				}
				
				if ((cliente.getCelular() != null) && (!cliente.getCelular().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "celular like '%" + cliente.getCelular().replaceAll("[- ]", "%").replaceAll("[()]", "%") + "%'";
				}
				
				if ((cliente.getLogradouro() != null) && (!cliente.getLogradouro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Logradouro like '%" + cliente.getLogradouro().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getNumero() != null) && (!cliente.getNumero().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numero like '%" + cliente.getNumero().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getBairro() != null) && (!cliente.getBairro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "bairro like '%" + cliente.getBairro().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getCidade() != null) && (!cliente.getCidade().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cidade like '%" + cliente.getCidade().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getEstado() != null) && (!cliente.getEstado().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Estado like '%" + cliente.getEstado().replace(" ", "%") + "%'";
				}
				
				if ((cliente.getCep() != null) && (!cliente.getCep().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cep like '%" + cliente.getCep().replaceAll("[- ]", "%") + "%'";
				}
				
				
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Cliente> list = new Vector<Cliente>();

			while (rst.next()) {
				Cliente clienteResult = new Cliente();
				clienteResult.setId(rst.getInt("idCliente"));
				clienteResult.setNome(rst.getString("nome"));
				clienteResult.setRG(rst.getString("rg"));
				clienteResult.setCPF(rst.getString("cpf"));
				clienteResult.setDataNascimento( rst.getDate("dtnasc") );
				clienteResult.setTelefone(rst.getString("telf"));
				clienteResult.setCelular(rst.getString("celular"));
				clienteResult.setLogradouro(rst.getString("logradouro"));
				clienteResult.setNumero(rst.getString("numero"));
				clienteResult.setBairro(rst.getString("bairro"));
				clienteResult.setCidade(rst.getString("cidade"));
				clienteResult.setEstado(rst.getString("estado"));
				clienteResult.setCep(rst.getString("cep"));
				list.add(clienteResult);
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
		Cliente cliente = new Cliente();
		cliente.setId(id);
		return this.findByPrimaryKey(cliente);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Cliente cliente = validate(obj);
		if ((cliente.getId() != null) && (cliente.getId() > 0)) {
			Cliente clienteFilter = new Cliente();
			clienteFilter.setId(cliente.getId());

			Object list[] = this.find(clienteFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
