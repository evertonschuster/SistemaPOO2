package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.IOTools;
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
		Produto produto = (Produto) obj;
		if ((produto.getId() != null) && (this.id  == produto.getId()) ) {
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
		if(id == null) {
			return "";
		}
		return IOTools.geradorDeToString( new String[]{id.toString(),descricao, this.marca.getDescricao()  },
				new Integer[]{3,12,14} );
	}

	public MyObject clone() {
		Produto produto = new Produto();
		produto.setId(this.id);
		produto.setDescricao(descricao);
		produto.setMarca((Marca) this.marca.clone());
		return produto;
	}

	public MyObject newInstance() {
		return new Produto();
	}

}
