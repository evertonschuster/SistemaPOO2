package br.edu.udc.sistemas.poo2.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Contribuinte extends MyObject{

	private Date dtNasc;
	private String telf;
	private String celular;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;

	public Date getDataNascimento() {
		return dtNasc;
	}

	public String getDataNascimentoString() {
		DateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
		return formatBR.format(dtNasc);

	}
	
	public void setDataNascimento(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getTelefone() {
		return telf;
	}

	public void setTelefone(String telf) {
		this.telf = telf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		System.out.println(this.toString() + " [==] " + obj.toString());
		if(!(obj instanceof Contribuinte)) {
			System.out.println("Safado");
			return false;
		}
		
		if (super.equals(obj)) {
			return true;
		}

		Contribuinte contribuinte = (Contribuinte) obj;
		if ((contribuinte.getDataNascimento() != null) && (this.dtNasc.equals(contribuinte.getDataNascimento()) )) {
			return true;
		}
		return false; 
	}


	public String toString() {
		return this.getId() + " - " + this.dtNasc 
				 + " - " + this.telf 
				 + " - " + this.celular 	
				 + " - " + this.logradouro
				 + " - " + this.numero 	
				 + " - " + this.bairro 	
				 + " - " + this.cidade 	
				 + " - " + this.estado 	
				 + " - " + this.cep; 		

	}

	public MyObject clone() {
		Contribuinte contribuinte = new Contribuinte();
		contribuinte.setId(this.id);

		contribuinte.setDataNascimento(this.dtNasc);
		contribuinte.setTelefone(this.telf);
		contribuinte.setCelular(this.celular);
		contribuinte.setLogradouro(this.logradouro);
		contribuinte.setNumero(this.numero);
		contribuinte.setBairro(this.bairro);
		contribuinte.setCidade(this.cidade);
		contribuinte.setEstado(this.estado);
		contribuinte.setCep(this.cep);
		

		return contribuinte;
	}

	public MyObject newInstance() {
		return new Contribuinte();
	}

}
