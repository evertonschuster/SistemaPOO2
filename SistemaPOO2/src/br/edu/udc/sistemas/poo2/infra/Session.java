package br.edu.udc.sistemas.poo2.infra;

public abstract class Session {
    
	protected DAO dao;

	public void save(Object obj) throws Exception {
		this.save(obj, true);
	}

	public void remove(Integer id) throws Exception {
		this.remove(id, true);
	}

	public void remove(Object obj) throws Exception {
		this.remove(obj, true);
	}

	public abstract void save(Object obj, Boolean bCommit) throws Exception;

	public abstract void remove(Integer id, Boolean bCommit) throws Exception;

	public abstract void remove(Object obj, Boolean bCommit) throws Exception;

	public abstract Object[] find(Object obj) throws Exception;

	public abstract Object findByPrimaryKey(Integer id) throws Exception;

	public abstract Object findByPrimaryKey(Object obj) throws Exception;
}