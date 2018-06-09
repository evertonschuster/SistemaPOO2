package br.edu.udc.sistemas.poo2.entity;


import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Servico extends MyObject {
	private String descricao;
	private Double valor;
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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
		Servico servico = (Servico) obj;
		if ((servico.getDescricao() != null) && (this.descricao.contains(servico.getDescricao()))) {
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
		if(id == null) {
			return "";
		}
		return IOTools.geradorDeToString( new String[]{id.toString(),descricao},
				new Integer[]{3,13} );
	}

	public MyObject clone() {
		Servico servico = new Servico();
		servico.setId(this.id);
		servico.setDescricao(descricao);
		servico.setValor(valor);
		return servico;
	}

	public MyObject newInstance() {
		return new Servico();
	}

}
