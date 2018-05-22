package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOMarca;
import br.edu.udc.sistemas.poo2.dao.DAOProduto;
import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionProduto extends Session {

	public SessionProduto() {
		this.dao = new DAOProduto();
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
		Object listProduto[] = this.dao.find(obj);

		DAOMarca daoMarca = new DAOMarca();

		for (int i = 0; i < listProduto.length; i++) {
			Produto Produto = (Produto) listProduto[i];
			Produto.setMarca((Marca) daoMarca.findByPrimaryKey(Produto.getMarca()));
		}

		return listProduto;
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