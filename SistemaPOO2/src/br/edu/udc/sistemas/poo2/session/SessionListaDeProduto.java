package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOListaDeProduto;
import br.edu.udc.sistemas.poo2.dao.DAOMarca;
import br.edu.udc.sistemas.poo2.dao.DAONota;
import br.edu.udc.sistemas.poo2.dao.DAOProduto;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionListaDeProduto extends Session {

	public SessionListaDeProduto() {
		this.dao = new DAOListaDeProduto();
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
		Object listListaDeProduto[] = this.dao.find(obj);
		
		DAONota daoNota = new DAONota();
		DAOProduto daoProduto = new DAOProduto();

		for (int i = 0; i < listListaDeProduto.length; i++) {
			ListaDeProduto listaDeProduto = (ListaDeProduto) listListaDeProduto[i];
			listaDeProduto.setNota((Nota) daoNota.findByPrimaryKey(listaDeProduto.getNota()));
			listaDeProduto.setProduto((Produto) daoProduto.findByPrimaryKey(listaDeProduto.getProduto()));
		}

		return listListaDeProduto;
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
