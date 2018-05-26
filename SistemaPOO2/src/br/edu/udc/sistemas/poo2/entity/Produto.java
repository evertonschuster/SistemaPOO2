package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Produto extends MyObject {
	private String descricao;
	private Double valor;
	private Integer qtd;
	private Integer qtdMinimo;
	private Marca marca;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Integer getQtdMinimo() {
		return qtdMinimo;
	}

	public void setQtdMinimo(Integer qtdMinimo) {
		this.qtdMinimo = qtdMinimo;
	}

	public Produto() {
		this.descricao = null;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
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
		Produto modelo = (Produto) obj;
		if ((modelo.getDescricao() != null) && (this.descricao.contains(modelo.getDescricao()))) {
			return true;
		}
		
		if ((modelo.getMarca() != null) && (this.marca != null) &&
		    (this.getMarca().getId() == modelo.getMarca().getId())){
			return true;
		} 
		return false;
	}

	@Override
	public String getString() {
		return super.getString() + ";" + this.descricao + ";" + marca.getString();
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.descricao = values[1];
		this.marca = new Marca();
		this.marca.setId(Integer.parseInt(values[2]));
		this.marca.setDescricao(values[3]);
	}
	
	public String toString() {
		return super.toString() + " - Descricao = " + this.descricao + " - " + this.marca;
	}

	public MyObject clone() {
		Produto modelo = new Produto();
		modelo.setId(this.id);
		modelo.setDescricao(descricao);
		modelo.setMarca((Marca) this.marca.clone());
		return modelo;
	}

	public MyObject newInstance() {
		return new Produto();
	}

}
