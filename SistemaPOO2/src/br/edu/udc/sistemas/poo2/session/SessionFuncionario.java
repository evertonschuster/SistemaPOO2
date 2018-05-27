package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFuncionario;

public class SessionFuncionario extends SessionCliente {

	public SessionFuncionario() {
		this.dao = new DAOFuncionario();
		//this.contribuinte = new SessionContribuinte();
	}

	@Override
	public void save(Object obj, Boolean bCommit) throws Exception {
		//this.contribuinte.save(obj, false);
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
		return this.dao.find(obj);
	}
	

	public Object findByLogin(Object obj) throws Exception {
		return ((DAOFuncionario)this.dao).findByLogin(obj);
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