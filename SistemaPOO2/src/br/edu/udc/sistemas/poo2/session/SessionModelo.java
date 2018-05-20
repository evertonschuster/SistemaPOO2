package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOMarca;
import br.edu.udc.sistemas.poo2.dao.DAOModelo;
import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.infra.Session;

public class SessionModelo extends Session {

	public SessionModelo() {
		this.dao = new DAOModelo();
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
		Object listModelo[] = this.dao.find(obj);

		DAOMarca daoMarca = new DAOMarca();

		for (int i = 0; i < listModelo.length; i++) {
			Modelo modelo = (Modelo) listModelo[i];
			modelo.setMarca((Marca) daoMarca.findByPrimaryKey(modelo.getMarca()));
		}

		return listModelo;
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