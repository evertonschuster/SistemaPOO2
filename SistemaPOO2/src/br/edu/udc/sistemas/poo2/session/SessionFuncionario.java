package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFuncionario;

public class SessionFuncionario extends SessionCliente {

	public SessionFuncionario() {
		this.dao = new DAOFuncionario();
		//this.contribuinte = new SessionContribuinte();
	}

	public Object findByLogin(Object obj) throws Exception {
		return ((DAOFuncionario)this.dao).findByLogin(obj);
	}

	

}