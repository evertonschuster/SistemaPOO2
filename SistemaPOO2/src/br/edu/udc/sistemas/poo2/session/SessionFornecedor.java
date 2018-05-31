package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFornecedor;

public class SessionFornecedor extends SessionContribuinte {

	//protected SessionContribuinte contribuinte;
	
		public SessionFornecedor() {
			this.dao = new DAOFornecedor();
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

		@Override
		public Object findByPrimaryKey(Integer id) throws Exception {
			return this.dao.findByPrimaryKey(id);
		}

		@Override
		public Object findByPrimaryKey(Object obj) throws Exception {
			return this.dao.findByPrimaryKey(obj);
		}

	}