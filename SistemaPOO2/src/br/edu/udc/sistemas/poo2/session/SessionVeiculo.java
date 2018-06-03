package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOModelo;
import br.edu.udc.sistemas.poo2.dao.DAOCliente;
import br.edu.udc.sistemas.poo2.dao.DAOVeiculo;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionVeiculo extends Session {

	public SessionVeiculo() {
		this.dao = new DAOVeiculo();
	}

	@Override
	public void save(Object obj, Boolean bCommit) throws Exception {
		this.dao.save(obj);
		if (bCommit) {
			this.dao.commit();
		}
	}

	@Override
	public void remove(Integer id, Boolean bCommit) throws Exception {
		this.dao.remove(id);
		if (bCommit) {
			this.dao.commit();
		}
	}

	@Override
	public void remove(Object obj, Boolean bCommit) throws Exception {
		this.dao.remove(obj);
		if (bCommit) {
			this.dao.commit();
		}
	}

	@Override
	public Object[] find(Object obj) throws Exception {
		Object listVeiculo[] = this.dao.find(obj);

		DAOModelo daoModelo = new DAOModelo();

		for (int i = 0; i < listVeiculo.length; i++) {
			Veiculo veiculo = (Veiculo) listVeiculo[i];
			veiculo.setModelo((Modelo) daoModelo.findByPrimaryKey(veiculo.getModelo()));
		}
		
		DAOCliente daoCliente = new DAOCliente();

		for (int i = 0; i < listVeiculo.length; i++) {
			Veiculo veiculo = (Veiculo) listVeiculo[i];
			veiculo.setCliente((Cliente) daoCliente.findByPrimaryKey(veiculo.getCliente()));
		}

		return listVeiculo;
	}

	@Override
	public Object findByPrimaryKey(Integer id) throws Exception {
		return this.dao.findByPrimaryKey(id);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Object listVeiculo = this.dao.findByPrimaryKey(obj);

		Veiculo veiculo = (Veiculo) listVeiculo;
		DAOModelo daoModelo = new DAOModelo();
		DAOCliente daoCliente = new DAOCliente();
		
		veiculo.setModelo((Modelo) daoModelo.findByPrimaryKey(veiculo.getModelo()));
		veiculo.setCliente((Cliente) daoCliente.findByPrimaryKey(veiculo.getCliente()));
	
		return listVeiculo;
	}

}