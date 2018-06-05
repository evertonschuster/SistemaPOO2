package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOListaDeServico;
import br.edu.udc.sistemas.poo2.dao.DAONota;
import br.edu.udc.sistemas.poo2.dao.DAOServico;
import br.edu.udc.sistemas.poo2.entity.ListaDeServico;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionListaDeServico extends Session {

	public SessionListaDeServico() {
		this.dao = new DAOListaDeServico();
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
		Object listListaDeServico[] = this.dao.find(obj);
		
		DAONota daoNota = new DAONota();
		DAOServico daoServico = new DAOServico();

		for (int i = 0; i < listListaDeServico.length; i++) {
			ListaDeServico listaDeServico = (ListaDeServico) listListaDeServico[i];
			listaDeServico.setNota((Nota) daoNota.findByPrimaryKey(listaDeServico.getNota()));
			listaDeServico.setServico((Servico) daoServico.findByPrimaryKey(listaDeServico.getServico()));
		}

		return listListaDeServico;
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
