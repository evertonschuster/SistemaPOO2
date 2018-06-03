package br.edu.udc.sistemas.poo2.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.infra.Database;

public class DAONotaVenda extends DAONota {
	private NotaVenda validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof NotaVenda))) {
			throw new Exception("Objeto nao e uma NotaVenda!");
		}
		return (NotaVenda) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		NotaVenda notaVenda = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = Database.getInstance().getConnection().createStatement();

			String sql;
			if ((notaVenda.getId() != null) && (notaVenda.getId() > 0)) {
				sql = "update nota set " +
						" descricao = '" + notaVenda.getDescricao() + "', " + 
						" Datas = '" + notaVenda.getData().toString() + "', " +
						" idFuncionario = '" + ((notaVenda.getFuncionario() != null) ? notaVenda.getFuncionario().getId() : "null") + "', " +
						" idVeiculo = '" + ((notaVenda.getVeiculo() != null) ? notaVenda.getVeiculo().getId() : "null")  + "', " + 
						" numeroDaNota = '" + notaVenda.getNumeroNota() + "', " +
						//" tipoNotaVenda = 'null', " +
						" idContribuinte = '" + ((notaVenda.getCliente() != null) ? notaVenda.getCliente().getId() : "null") + "' " + 
						" where idnota = " + notaVenda.getId();
				
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into nota (Datas, idFuncionario, idVeiculo, numeroDaNota, tipoNota, idContribuinte,descricao) values(" + 
						"'" + notaVenda.getData().toString() + "', " +
						"'" + ((notaVenda.getFuncionario() != null) ? notaVenda.getFuncionario().getId() : "null") + "', " +
						"'" + ((notaVenda.getVeiculo() != null) ? notaVenda.getVeiculo().getId() : "null") + "', " +
						"'" + notaVenda.getNumeroNota() + "', " +
						"'" + notaVenda.getTipoNota() + "', " +
						"'" + ((notaVenda.getCliente() != null) ? notaVenda.getCliente().getId() : "null") + "', " +
						"'" + notaVenda.getDescricao() + "')";
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					notaVenda.setId(rst.getInt(1));
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
		NotaVenda notaVenda = new NotaVenda();
		notaVenda.setId(id);
		this.remove(notaVenda);
	}

	@Override
	public void remove(Object obj) throws Exception {
		NotaVenda notaVenda = validate(obj);
		Statement stmt = null;
		try {
			if ((notaVenda.getId() != null) && (notaVenda.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from nota where idnota = " + notaVenda.getId();
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
			String sql = "select idnota, descricao, Datas, idFuncionario, numeroDaNota, tipoNota, idContribuinte, idVeiculo from nota  where tipoNota = 'venda'";

			if (obj != null) {
				NotaVenda notaVenda = validate(obj);


				if ((notaVenda.getId() != null) && (notaVenda.getId() > 0)) {
					sql = sql + " AND idnota = " + notaVenda.getId();
				}

				if ((notaVenda.getDescricao() != null) && (!notaVenda.getDescricao().trim().equals(""))) {
					
					sql = sql + " AND descricao like '%" + notaVenda.getDescricao().replace(" ", "%") + "%'";
				}
				
				if ((notaVenda.getData() != null) && (!notaVenda.getData().toString().contains("  "))) {
					sql = sql + " AND Datas = '" + notaVenda.getData() + "' ";
				}
				
				if ((notaVenda.getFuncionario() != null) && (notaVenda.getFuncionario().getId() != 0)) {
					sql = sql + " AND idFuncionario = '" + notaVenda.getFuncionario().getId() + "'";
				}
				
				if ((notaVenda.getNumeroNota() != null) && (notaVenda.getNumeroNota() != 0)) {
					sql = sql + " AND numeroDaNota = '" + notaVenda.getNumeroNota() + "'";
				}
				
				if ((notaVenda.getCliente() != null) && (notaVenda.getCliente().getId() != 0)) {
					sql = sql + " AND idContribuinte = '" + notaVenda.getCliente().getId() + "'";
				}
				
				if ((notaVenda.getVeiculo() != null) && (notaVenda.getVeiculo().getId() != 0)) {
					sql = sql + " AND idVeiculo = '" + notaVenda.getVeiculo().getId() + "'";
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<NotaVenda> list = new Vector<NotaVenda>();
			while (rst.next()) {
				NotaVenda notaVendaResult = new NotaVenda();
				notaVendaResult.setId(rst.getInt("idnota"));
				notaVendaResult.setDescricao(rst.getString("descricao")); //, numeroDaNotaVenda, tipoNotaVenda
				notaVendaResult.setData(rst.getDate("Datas"));
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rst.getInt("idFuncionario"));
				notaVendaResult.setFuncionario(funcionario);
				
				Cliente cliente = new Cliente();
				cliente.setId(rst.getInt("idContribuinte"));
				notaVendaResult.setCliente(cliente);
				
				Veiculo veiculo = new Veiculo();
				veiculo.setId(rst.getInt("idVeiculo"));
				notaVendaResult.setVeiculo(veiculo);
				
				notaVendaResult.setNumeroNota(rst.getInt("numeroDaNota"));
				notaVendaResult.setTipoNota(rst.getString("tipoNota"));
				list.add(notaVendaResult);
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
		NotaVenda notaVenda = new NotaVenda();
		notaVenda.setId(id);
		return this.findByPrimaryKey(notaVenda);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		NotaVenda notaVenda = validate(obj);
		if ((notaVenda.getId() != null) && (notaVenda.getId() > 0)) {
			NotaVenda notaVendaFilter = new NotaVenda();
			notaVendaFilter.setId(notaVenda.getId());

			Object list[] = this.find(notaVendaFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
