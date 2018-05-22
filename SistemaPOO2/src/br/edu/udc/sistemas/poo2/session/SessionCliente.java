package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOCliente;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionCliente extends Session {

	public SessionCliente() {
		this.dao = new DAOCliente();
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
		Object listCliente[] = this.dao.find(obj);

		for (int i = 0; i < listCliente.length; i++) {
			Cliente Cliente = (Cliente) listCliente[i];
		}

		return listCliente;
	}

	@Override
	public Object findByPrimaryKey(Integer id) throws Exception {
		return this.dao.findByPrimaryKey(id);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		return this.dao.findByPrimaryKey(obj);
	}

}