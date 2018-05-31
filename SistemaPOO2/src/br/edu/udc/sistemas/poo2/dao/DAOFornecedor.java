package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.postgresql.util.PSQLException;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;

public class DAOFornecedor extends DAOContribuinte {

	private Fornecedor validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Fornecedor))) {
			throw new ExceptionValidacao("Objeto nao e um Fornecedor!");
		}
		return (Fornecedor) obj;
	}
	

	@Override
	public void save(Object obj) throws Exception {
		Fornecedor fornecedor = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			
			if ((fornecedor.getId() != null) && (fornecedor.getId() > 0)) {
				super.save(obj);
				sql = "update Fornecedor set " 
						+ " razaoSocial = '" + fornecedor.getRazaoSocial() +"' " 
						+ ", NomeFantazia = '" + fornecedor.getNomeFantazia()  +"' " 
						+ ", CNPJ = '" + fornecedor.getCNPJ()  +"' " 
						+ " where idFornecedor = " + fornecedor.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				
				Fornecedor fornecedorFind = new Fornecedor();
				fornecedorFind.setCNPJ(fornecedor.getCNPJ());
				if( !(this.find(fornecedorFind).length == 0)) {
					throw new ExceptionValidacao("Fornecedor ja Cadastrado!");
				}
				
				
				super.save(obj);
				sql = "insert into Fornecedor (idFornecedor, NomeFantazia, razaoSocial, CNPJ) " 
						+ " values('" + 
						fornecedor.getId() + "', '" +  
						fornecedor.getNomeFantazia() + "', '" + 
						fornecedor.getRazaoSocial()  + "', '" +  
						fornecedor.getCNPJ() + "')";
				
				
				System.out.println(sql);
				stmt.execute(sql);
				/*stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					fornecedor.setId(rst.getInt(1));
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
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		this.remove(fornecedor);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Fornecedor fornecedor = validate(obj);
		Statement stmt = null;
		try {
			if ((fornecedor.getId() != null) && (fornecedor.getId() > 0)) {
				
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Fornecedor " + "where idFornecedor = " + fornecedor.getId();
				System.out.println(sql);
				stmt.execute(sql);
				super.remove(obj);
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
			String sql = "select idFornecedor, NomeFantazia, razaoSocial, CNPJ, dtnasc, telf, celular, logradouro, numero, bairro, cidade, estado, cep " +
					" from Fornecedor " + 
					" INNER JOIN Contribuinte ON Contribuinte.idContribuinte = Fornecedor.idFornecedor";


			if (obj != null) {
				Fornecedor fornecedor = validate(obj);

				Boolean bWhere = false;
				if ((fornecedor.getId() != null) && (fornecedor.getId() > 0)) {
					sql = sql + " where idFornecedor = " + fornecedor.getId();
					bWhere = true;
				}

				if ((fornecedor.getNomeFantazia() != null) && (!fornecedor.getNomeFantazia().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "NomeFantazia like '%" + fornecedor.getNomeFantazia().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getRazaoSocial() != null) && (!fornecedor.getRazaoSocial().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "NomeFantazia like '%" + fornecedor.getNomeFantazia().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getCNPJ() != null) && (!fornecedor.getCNPJ().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "CNPJ like '%" + fornecedor.getCNPJ().replace(" ", "%") + "%'";
				}
				
				if(fornecedor.getDataNascimento() != null) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "dtnasc = '" + fornecedor.getDataNascimento().toString() + "' ";
				}
				
				if ((fornecedor.getTelefone() != null) && (!fornecedor.getTelefone().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "telf like '%" + fornecedor.getTelefone().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getCelular() != null) && (!fornecedor.getCelular().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "celular like '%" + fornecedor.getCelular().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getLogradouro() != null) && (!fornecedor.getLogradouro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Logradouro like '%" + fornecedor.getLogradouro().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getNumero() != null) && (!fornecedor.getNumero().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "numero like '%" + fornecedor.getNumero().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getBairro() != null) && (!fornecedor.getBairro().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "bairro like '%" + fornecedor.getBairro().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getCidade() != null) && (!fornecedor.getCidade().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cidade like '%" + fornecedor.getCidade().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getEstado() != null) && (!fornecedor.getEstado().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "Estado like '%" + fornecedor.getEstado().replace(" ", "%") + "%'";
				}
				
				if ((fornecedor.getCep() != null) && (!fornecedor.getCep().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cep like '%" + fornecedor.getCep().replace(" ", "%") + "%'";
				}
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Fornecedor> list = new Vector<Fornecedor>();

			while (rst.next()) {
				Fornecedor fornecedorResult = new Fornecedor();
				fornecedorResult.setId(rst.getInt("idFornecedor"));
				fornecedorResult.setNomeFantazia(rst.getString("NomeFantazia"));
				fornecedorResult.setRazaoSocial(rst.getString("razaoSocial"));
				fornecedorResult.setCNPJ(rst.getString("CNPJ"));
				fornecedorResult.setDataNascimento( rst.getDate("dtnasc") );
				fornecedorResult.setTelefone(rst.getString("telf"));
				fornecedorResult.setCelular(rst.getString("celular"));
				fornecedorResult.setLogradouro(rst.getString("logradouro"));
				fornecedorResult.setNumero(rst.getString("numero"));
				fornecedorResult.setBairro(rst.getString("bairro"));
				fornecedorResult.setCidade(rst.getString("cidade"));
				fornecedorResult.setEstado(rst.getString("estado"));
				fornecedorResult.setCep(rst.getString("cep"));
				list.add(fornecedorResult);
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
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		return this.findByPrimaryKey(fornecedor);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Fornecedor fornecedor = validate(obj);
		if ((fornecedor.getId() != null) && (fornecedor.getId() > 0)) {
			Fornecedor fornecedorFilter = new Fornecedor();
			fornecedorFilter.setId(fornecedor.getId());

			Object list[] = this.find(fornecedorFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
