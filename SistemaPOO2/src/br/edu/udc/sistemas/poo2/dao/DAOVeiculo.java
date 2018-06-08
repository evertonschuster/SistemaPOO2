package br.edu.udc.sistemas.poo2.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.infra.DAO;
import br.edu.udc.sistemas.poo2.infra.Database;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class DAOVeiculo extends DAO {

	private Veiculo validate(Object obj) throws Exception {
		if ((obj == null) || (!(obj instanceof Veiculo))) {
			throw new Exception("Objeto nao e uma Veiculo!");
		}
		return (Veiculo) obj;
	}

	@Override
	public void save(Object obj) throws Exception {
		Veiculo veiculo = validate(obj);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = Database.getInstance().getConnection().createStatement();
			String sql;
			if ((veiculo.getId() != null) && (veiculo.getId() > 0)) {
				sql = "update Veiculo set "+
						" ano = '" + veiculo.getAno() + "'," + 
						" placa = '" + veiculo.getPlaca() + "'," + 
						" chassis = '" + veiculo.getChassis() +  "'," + 
						" cor = '" + veiculo.getCor() + "'," + 
						" idModelo = '" + ((veiculo.getModelo() != null) ? veiculo.getModelo().getId() : "null") + "', "  +
						" idCliente = '" + ((veiculo.getCliente() != null) ? veiculo.getCliente().getId() : "null") + "' "  +
						" where idVeiculo = " + veiculo.getId();
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = "insert into Veiculo (ano,placa,chassis,cor,idModelo,idCliente) " + 
						"values('" +  veiculo.getAno() + "','" +
						veiculo.getPlaca() + "','" + 
						veiculo.getChassis() + "','" + 
						veiculo.getCor() + "','" + 
						((veiculo.getModelo() != null) ? veiculo.getModelo().getId() : "null") + "', '" +
						((veiculo.getCliente() != null) ? veiculo.getCliente().getId() : "null") + "' )";
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					veiculo.setId(rst.getInt(1));
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
		Veiculo Veiculo = new Veiculo();
		Veiculo.setId(id);
		this.remove(Veiculo);
	}

	@Override
	public void remove(Object obj) throws Exception {
		Veiculo Veiculo = validate(obj);
		Statement stmt = null;
		try {
			if ((Veiculo.getId() != null) && (Veiculo.getId() > 0)) {
				stmt = Database.getInstance().getConnection().createStatement();
				String sql = "delete from Veiculo " + "where idVeiculo = " + Veiculo.getId();
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
			String sql = "select idVeiculo,ano,idmodelo,placa,chassis,cor,idcliente from Veiculo";

			if (obj != null) {
				Veiculo Veiculo = validate(obj);

				Boolean bWhere = false;
				if ((Veiculo.getId() != null) && (Veiculo.getId() > 0)) {
					sql = sql + " where idVeiculo = " + Veiculo.getId();
					bWhere = true;
				}

				if ((Veiculo.getAno() != null) && (!Veiculo.getAno().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "ano like '%" + Veiculo.getAno().replace(" ", "%") + "%'";
				}

				if ((Veiculo.getPlaca() != null) && (!Veiculo.getPlaca().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "placa like '%" + Veiculo.getPlaca().replaceAll("[- ]", "%") + "%'";
				}
				
				if ((Veiculo.getChassis() != null) && (!Veiculo.getChassis().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "chassis like '%" + Veiculo.getChassis().replace(" ", "%") + "%'";
				}
				
				if ((Veiculo.getCor() != null) && (!Veiculo.getCor().trim().equals(""))) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "cor like '%" + Veiculo.getCor().replace(" ", "%") + "%'";
				}

				if ((Veiculo.getCliente() != null) && (Veiculo.getCliente().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idcliente = " + Veiculo.getCliente().getId();
				}
				
				if ((Veiculo.getModelo() != null) && (Veiculo.getModelo().getId() != null)) {
					if (bWhere) {
						sql = sql + " and ";
					} else {
						sql = sql + " where ";
						bWhere = true;
					}
					sql = sql + "idModelo = " + Veiculo.getModelo().getId();
				}
			}

			System.out.println(sql);
			rst = stmt.executeQuery(sql);

			Vector<Veiculo> list = new Vector<Veiculo>();
			while (rst.next()) {
				Veiculo VeiculoResult = new Veiculo();
				VeiculoResult.setId(rst.getInt("idVeiculo"));
				VeiculoResult.setAno(rst.getString("ano"));
				VeiculoResult.setPlaca(rst.getString("placa"));
				VeiculoResult.setChassis(rst.getString("chassis"));
				VeiculoResult.setCor(rst.getString("cor"));
				

				Cliente cliente = new Cliente();
				cliente.setId(rst.getInt("idCliente"));
				VeiculoResult.setCliente(cliente);
				
				Modelo modelo = new Modelo();
				modelo.setId(rst.getInt("idModelo"));
				VeiculoResult.setModelo(modelo);

				list.add(VeiculoResult);
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
		Veiculo Veiculo = new Veiculo();
		Veiculo.setId(id);
		return this.findByPrimaryKey(Veiculo);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Veiculo Veiculo = validate(obj);
		if ((Veiculo.getId() != null) && (Veiculo.getId() > 0)) {
			Veiculo VeiculoFilter = new Veiculo();
			VeiculoFilter.setId(Veiculo.getId());

			Object list[] = this.find(VeiculoFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
