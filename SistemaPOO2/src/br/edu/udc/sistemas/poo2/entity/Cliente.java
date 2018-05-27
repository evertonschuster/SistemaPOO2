package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Cliente extends Contribuinte {
	private String nome;
	private String RG;
	private String CPF;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}


	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		Cliente Cliente = (Cliente) obj;
		if ((Cliente.getNome() != null) && (this.nome.contains(Cliente.getNome()))) {
			return true;
		}

		return false;
	}

	@Override
	
	public String getString() {
		return super.getString() + ";" + this.nome ;
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.nome = values[1];
		this.RG = values[2];
		this.CPF = values[3];
	}
	
	public String toString() {
		return this.getId() + " - " + this.nome + " - " + this.getCPF() ;
	}

	public MyObject clone() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setNome(nome);
		cliente.setCPF(CPF);
		cliente.setRG(RG);
		super.clone();
		return cliente;
	}

	public MyObject newInstance() {
		return new Cliente();
	}

}
