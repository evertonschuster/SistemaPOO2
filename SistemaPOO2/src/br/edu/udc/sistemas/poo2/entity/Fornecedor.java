package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Fornecedor extends Contribuinte {

	public String getNomeFantazia() {
		return NomeFantazia;
	}

	public void setNomeFantazia(String nomeFantazia) {
		NomeFantazia = nomeFantazia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	protected String NomeFantazia;
	protected String razaoSocial;
	protected String CNPJ;



	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		System.out.println(this.toString() + " [==] " + obj.toString());
		if(!(obj instanceof Fornecedor)) {
			System.out.println("Safado");
			return false;
		}
		
		Fornecedor fornecedor = (Fornecedor) obj;
		if ((fornecedor.getId() != null) && (this.id == fornecedor.getId())) {
			return true;
		}

		return false;
	}

	@Override
	
	public String getString() {
		return super.getString() + ";" + this.NomeFantazia ;
	}
	
	public String toString() {		
		return IOTools.geradorDeToString( new String[]{id.toString(),NomeFantazia, CNPJ},
				new Integer[]{3,12,18});
	}
	
	public MyObject clone() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(this.id);
		fornecedor.setNomeFantazia(this.NomeFantazia);
		fornecedor.setRazaoSocial(this.razaoSocial);
		fornecedor.setCNPJ(this.CNPJ);
		super.clone();
		return fornecedor;
	}

	public MyObject newInstance() {
		return new Fornecedor();
	}

}
