package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFuncionario;
import br.edu.udc.sistemas.poo2.dao.DAOListaDeProduto;
import br.edu.udc.sistemas.poo2.dao.DAONota;
import br.edu.udc.sistemas.poo2.dao.DAOProduto;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionNota extends Session {

	public SessionNota() {
		this.dao = new DAONota();
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
		SessionListaDeProduto sessionListaDeProduto = new SessionListaDeProduto();
		Nota nota = new Nota();
		nota.setId(id);
		ListaDeProduto listaDeProduto = new ListaDeProduto();
		listaDeProduto.setNota(nota);
		sessionListaDeProduto.remove(nota, false);
		this.dao.remove(id);
		if (bCommit) {
			this.dao.commit();
		}
	}

	@Override
	public void remove(Object obj, Boolean bCommit) throws Exception {
		SessionListaDeProduto sessionListaDeProduto = new SessionListaDeProduto();
		ListaDeProduto listaDeProduto = new ListaDeProduto();
		listaDeProduto.setNota((Nota)obj);
		sessionListaDeProduto.remove(listaDeProduto, false);
		this.dao.remove(obj);
		if (bCommit) {
			this.dao.commit();
		}
	}

	@Override
	public Object[] find(Object obj) throws Exception {
		Object listnota[] = this.dao.find(obj);
		
		DAOFuncionario daoFuncionario = new DAOFuncionario();

		for (int i = 0; i < listnota.length; i++) {
			Nota nota = (Nota) listnota[i];
			nota.setFuncionario( (Funcionario) daoFuncionario.findByPrimaryKey(nota.getFuncionario()) );
		}
		
		return listnota;
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