package br.edu.udc.sistemas.poo2.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Nota extends MyObject {
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDataString() {
		DateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
		return formatBR.format(data);

	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ListaDeProduto[] getListadeProdutos() {
		return listadeProdutos;
	}
	public void setListadeProdutos(ListaDeProduto[] listadeProdutos) {
		this.listadeProdutos = listadeProdutos;
	}
	public Integer getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}

	protected Date data;
	protected Funcionario funcionario; 
	//protected Funcionario funcionario;
	protected String descricao;
	protected ListaDeProduto[] listadeProdutos;
	protected Integer numeroNota;
	protected String tipoNota;


	public Nota() {
		this.descricao = null;
		this.data = null;
		//this.funcionario = null;
		this.funcionario = null;
		this.listadeProdutos = null;
		this.numeroNota = null;
		this.tipoNota = null;
	}

	
	

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		System.out.println(this.toString() + " [==] " + obj.toString());
		if(!(obj instanceof Nota)) {
			System.out.println("Safado");
			return false;
		}
		
		if (super.equals(obj)) {
			return true;
		}

		Nota nota = (Nota) obj;
		if ((nota.getId() != null) && (this.getId() == nota.getId())) {
			return true;
		}
		return false; 
	}


	public String toString() {
		return this.getId() + " | " + this.descricao;
	}

	public MyObject clone() {
		Nota nota = new Nota();
		nota.setId(this.id);
		nota.setData(this.data);
		//nota.setFuncionario(funcionario);
		nota.setFuncionario(this.funcionario);
		nota.setDescricao(this.descricao);
		nota.setNumeroNota(this.numeroNota);
		nota.setListadeProdutos(this.listadeProdutos);

		nota.setDescricao(this.descricao);

		return nota;
	}

	public MyObject newInstance() {
		return new Nota();
	}
}
