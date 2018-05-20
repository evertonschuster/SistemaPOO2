package br.edu.udc.sistemas.poo2.infra;

public abstract class DAO {

	public void commit() throws Exception {
		Database.getInstance().getConnection().commit();
	}

	public void rollback() throws Exception {
		Database.getInstance().getConnection().rollback();
	}

	public abstract void save(Object obj) throws Exception;

	public abstract void remove(Integer id) throws Exception;

	public abstract void remove(Object obj) throws Exception;

	public abstract Object[] find(Object obj) throws Exception;

	public abstract Object findByPrimaryKey(Integer id) throws Exception;

	public abstract Object findByPrimaryKey(Object obj) throws Exception;
}
