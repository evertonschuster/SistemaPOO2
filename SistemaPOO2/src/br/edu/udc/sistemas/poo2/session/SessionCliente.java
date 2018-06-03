package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOCliente;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionCliente extends SessionContribuinte {

	//protected SessionContribuinte contribuinte;
	
	public SessionCliente() {
		this.dao = new DAOCliente();
		//this.contribuinte = new SessionContribuinte();
	}


}