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
				sql = "update Cliente set nome = '" + Cliente.getNome() + "'," + "rg = " + Cliente.getRG() + "'," + "cpf = " + Cliente.getCPF() + "'," + "dtNasc = " + Cliente.getDtNasc() + "'," + "telefone = " + Cliente.getTelf() + "'," + "celular = " + Cliente.getCelular() + "'," + "logradouro = " + Cliente.getLogradoudo() + "'," + "numero = " + Cliente.getNumero() + "'," + "bairro = " + Cliente.getBairro() + "'," + "cidade = " + Cliente.getCidade() + "'," + "estado = " + Cliente.getEstado() + "'," + "cep = " + Cliente.getCep() + " "  + "where idCliente = " + Cliente.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Cliente (nome,rg,cpf,dtNasc,telf,celular,logradouro,numero,bairro,cidade,estado,cep) " + "values('" + Cliente.getNome() + "'," + Cliente.getRG() + "'," + Cliente.getCPF() + "'," + Cliente.getDtNasc() + "'," + Cliente.getTelf() + "'," + Cliente.getCelular() + "'," + Cliente.getLogradoudo() + "'," + Cliente.getNumero() + "'," + Cliente.getBairro() + "'," + Cliente.getCidade()  + "'," + Cliente.getEstado() + "'," + Cliente.getCep()  + ")";
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
			String sql = "select idCliente,nome,rg,cpf,dtNasc,telefone,celular,logradouro,numero,bairro,cidade,estado,cep from Cliente";

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
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Cliente> list = new Vector<Cliente>();
			while (rst.next()) {
				Cliente ClienteResult = new Cliente();
				ClienteResult.setId(rst.getInt("idCliente"));
				ClienteResult.setNome(rst.getString("nome"));
				//ClienteResult.setRG(rst.getString("rg"));
				//ClienteResult.setCPF(rst.getString("cpf"));
				ClienteResult.setDtNasc(rst.getString("dtNasc"));
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
