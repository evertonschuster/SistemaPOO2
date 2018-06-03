package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFornecedor;

public class SessionFornecedor extends SessionContribuinte {

	//protected SessionContribuinte contribuinte;
	
		public SessionFornecedor() {
			this.dao = new DAOFornecedor();
			//this.contribuinte = new SessionContribuinte();
		}

		
	}