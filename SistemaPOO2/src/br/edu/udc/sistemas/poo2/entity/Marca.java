package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Marca extends MyObject {
	private String descricao;

	public Marca() {
		this.descricao = null;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		System.out.println(this.toString() + " [==] " + obj.toString());
		if(!(obj instanceof Marca)) {
			System.out.println("Safado");
			return false;
		}
		
		if (super.equals(obj)) {
			return true;
		}

		Marca marca = (Marca) obj;
		if ((marca.getDescricao() != null) && (this.descricao.contains(marca.getDescricao()))) {
			return true;
		}
		return false;
	}

	@Override
	public String getString() {
		return super.getString() + ";" + this.descricao;
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.descricao = values[1];
	}

	public String toString() {
		return this.getId() + " - " + this.descricao;
	}

	public MyObject clone() {
		Marca marca = new Marca();
		marca.setId(this.id);

		marca.setDescricao(descricao);

		return marca;
	}

	public MyObject newInstance() {
		return new Marca();
	}

}
