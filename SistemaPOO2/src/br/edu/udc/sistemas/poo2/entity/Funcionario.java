package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Funcionario extends Cliente {
	protected String login;
	protected String senha;
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		
		return false;
	}

	public String toString() {
		return this.getId() + " - " + this.nome + " - " + this.getCPF() + " - " + this.login;
	}

	public MyObject clone() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(this.id);
		funcionario.setLogin(this.login);
		funcionario.setSenha(this.senha);
		super.clone();
		return funcionario;
	}

	public MyObject newInstance() {
		return new Funcionario();
	}

}