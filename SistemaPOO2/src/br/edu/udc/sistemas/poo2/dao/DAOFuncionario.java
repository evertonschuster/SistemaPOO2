package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;

public class DAOFuncionario extends DAOCliente {

	private Funcionario validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Funcionario))) {
			throw new ExceptionValidacao("Objeto nao e um Funcionario!");
		}
		return (Funcionario) obj;
	}
	

	@Override
	public void save(Object obj) throws Exception {
		Funcionario funcionario = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			
			if ((funcionario.getId() != null) && (funcionario.getId() > 0)) {
				super.save(obj);
				
				if(this.find(obj).length == 0) {
					sql = "INSERT INTO Funcionario (idFucionario, login, senha) " 
							+ " VALUES('" + funcionario.getId() + "', '" +  funcionario.getLogin() + "', '" + funcionario.getSenha()  +  "')";
				
				}else {
					sql = "UPDATE Funcionario set " 
							+ "login = '" + funcionario.getLogin() +"' " 
							+ ", senha = '" + funcionario.getSenha()  +"' " 
							+ " WHERE idFucionario = " + funcionario.getId();
				}
				

				System.out.println(sql);
				stmt.execute(sql);

					
					
				
			} else {
				
				Funcionario funcionarioFind = new Funcionario();
				funcionarioFind.setCPF(funcionario.getCPF());
				if( !(this.find(funcionarioFind).length == 0)) {
					throw new ExceptionValidacao("Funcionario ja Cadastrado!");
				}
				
				
				super.save(obj);
				sql = "INSERT INTO Funcionario (idFucionario, login, senha) " 
						+ " VALUES('" + funcionario.getId() + "', '" +  funcionario.getLogin() + "', '" + funcionario.getSenha()  +  "')";
				
				
				System.out.println(sql);
				stmt.execute(sql);
				/*stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					funcionario.setId(rst.getInt(1));
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
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		this.remove(funcionario);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Funcionario funcionario = validate(obj);
		Statement stmt = null;
		try {
			if ((funcionario.getId() != null) && (funcionario.getId() > 0)) {
				
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "DELETE FROM Funcionario " + "WHERE idFucionario = " + funcionario.getId();
				System.out.println(sql);
				stmt.execute(sql);
				//super.remove(obj);
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
		if((!(obj instanceof Funcionario)) && (obj instanceof Cliente)){
			return super.find(obj);			
		}
			
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql = "SELECT idFucionario, nome, rg, cpf, dtnasc, telf, celular, logradouro, numero, bairro, cidade, estado, cep, login, senha " +
					" FROM funcionario " +
					" INNER JOIN Cliente ON Cliente.idCliente = funcionario.idFucionario " + 
					" INNER JOIN Contribuinte ON Contribuinte.idContribuinte = Cliente.idCliente";


			if (obj != null) {
				Funcionario funcionario = validate(obj);

				Boolean bWhere = false;
				if ((funcionario.getId() != null) && (funcionario.getId() > 0)) {
					sql = sql + " where idFucionario = " + funcionario.getId();
					bWhere = true;
				}

				if ((funcionario.getNome() != null) && (!funcionario.getNome().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "nome like '%" + funcionario.getNome().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getRG() != null) && (!funcionario.getRG().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "rg like '%" + funcionario.getRG().replaceAll("[. ]", "%").replaceAll("[-]", "%") + "%'";
				}
				
				if ((funcionario.getCPF() != null) && (!funcionario.getCPF().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cpf like '%" + funcionario.getCPF().replaceAll("[. ]", "%").replaceAll("[-]", "%") + "%'";
				}
				
				if(funcionario.getDataNascimento() != null) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "dtnasc = '" + funcionario.getDataNascimento().toString() + "' ";
				}
				
				if ((funcionario.getTelefone() != null) && (!funcionario.getTelefone().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "telf like '%" + funcionario.getTelefone().replaceAll("[- ]", "%").replaceAll("[()]", "%") + "%'";
				}
				
				if ((funcionario.getCelular() != null) && (!funcionario.getCelular().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "celular like '%" + funcionario.getCelular().replaceAll("[- ]", "%").replaceAll("[()]", "%") + "%'";
				}
				
				if ((funcionario.getLogradouro() != null) && (!funcionario.getLogradouro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Logradouro like '%" + funcionario.getLogradouro().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getNumero() != null) && (!funcionario.getNumero().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numero like '%" + funcionario.getNumero().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getBairro() != null) && (!funcionario.getBairro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "bairro like '%" + funcionario.getBairro().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getCidade() != null) && (!funcionario.getCidade().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cidade like '%" + funcionario.getCidade().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getEstado() != null) && (!funcionario.getEstado().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Estado like '%" + funcionario.getEstado().replace(" ", "%") + "%'";
				}
				
				if ((funcionario.getCep() != null) && (!funcionario.getCep().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cep like '%" + funcionario.getCep().replaceAll("[- ]", "%") + "%'";
				}
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Funcionario> list = new Vector<Funcionario>();

			while (rst.next()) {
				Funcionario funcionarioResult = new Funcionario();
				funcionarioResult.setId(rst.getInt("idFucionario"));
				funcionarioResult.setNome(rst.getString("nome"));
				funcionarioResult.setRG(rst.getString("rg"));
				funcionarioResult.setCPF(rst.getString("cpf"));
				funcionarioResult.setDataNascimento( rst.getDate("dtnasc") );
				funcionarioResult.setTelefone(rst.getString("telf"));
				funcionarioResult.setCelular(rst.getString("celular"));
				funcionarioResult.setLogradouro(rst.getString("logradouro"));
				funcionarioResult.setNumero(rst.getString("numero"));
				funcionarioResult.setBairro(rst.getString("bairro"));
				funcionarioResult.setCidade(rst.getString("cidade"));
				funcionarioResult.setEstado(rst.getString("estado"));
				funcionarioResult.setCep(rst.getString("cep"));
				funcionarioResult.setLogin(rst.getString("login"));
				funcionarioResult.setSenha(rst.getString("senha"));
				list.add(funcionarioResult);
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
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		return this.findByPrimaryKey(funcionario);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Funcionario funcionario = validate(obj);
		if ((funcionario.getId() != null) && (funcionario.getId() > 0)) {
			Funcionario funcionarioFilter = new Funcionario();
			funcionarioFilter.setId(funcionario.getId());

			Object list[] = this.find(funcionarioFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}


	public Object findByLogin(Object obj) throws Exception {
		if(obj == null) {
			return null;
		}
		
		Funcionario funcionario = validate(obj);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql = "SELECT idFucionario, nome, rg, cpf, dtnasc, telf, celular, logradouro, numero, bairro, cidade, estado, cep, login, senha " +
					" FROM funcionario " +
					" INNER JOIN Cliente ON Cliente.idCliente = funcionario.idFucionario " + 
					" INNER JOIN Contribuinte ON Contribuinte.idContribuinte = Cliente.idCliente" +
					" WHERE funcionario.login = '" + funcionario.getLogin() + "' " +
					" AND funcionario.senha = '" + funcionario.getSenha() + "'";


			
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Funcionario funcionarioResult = new Funcionario();
			while (rst.next()) {

				funcionarioResult.setId(rst.getInt("idFucionario"));
				funcionarioResult.setNome(rst.getString("nome"));
				funcionarioResult.setRG(rst.getString("rg"));
				funcionarioResult.setCPF(rst.getString("cpf"));
				funcionarioResult.setDataNascimento( rst.getDate("dtnasc") );
				funcionarioResult.setTelefone(rst.getString("telf"));
				funcionarioResult.setCelular(rst.getString("celular"));
				funcionarioResult.setLogradouro(rst.getString("logradouro"));
				funcionarioResult.setNumero(rst.getString("numero"));
				funcionarioResult.setBairro(rst.getString("bairro"));
				funcionarioResult.setCidade(rst.getString("cidade"));
				funcionarioResult.setEstado(rst.getString("estado"));
				funcionarioResult.setCep(rst.getString("cep"));
				funcionarioResult.setLogin(rst.getString("login"));
				funcionarioResult.setSenha(rst.getString("senha"));
			}
				
			
			// Object listResult[] = new Object[list.size()];
			// for (int i = 0; i < listResult.length; i++) {
			// listResult[i] = list.get(i);
			// }
			// return listResult;

			return funcionarioResult;
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
}
