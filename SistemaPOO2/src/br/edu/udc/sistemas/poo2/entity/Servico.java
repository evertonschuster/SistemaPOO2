package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Servico extends MyObject {
	private String descricao;
	private Float valor;
	

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Servico() {
		this.descricao = null;
	}

	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		Servico Servico = (Servico) obj;
		if ((Servico.getDescricao() != null) && (this.descricao.contains(Servico.getDescricao()))) {
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
		return super.toString() + " - Descricao = " + this.descricao;
	}

	public MyObject clone() {
		Servico Servico = new Servico();
		Servico.setId(this.id);
		Servico.setDescricao(descricao);
		Servico.setValor(valor);
		return Servico;
	}

	public MyObject newInstance() {
		return new Servico();
	}

}
