package br.edu.udc.sistemas.poo2.entity;


import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Cliente extends Contribuinte {
	private String nome;
	private String RG;
	private String CPF;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
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

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		
		System.out.println(this.toString() + " [==] " + obj.toString());
		if(!(obj instanceof Cliente)) {
			System.out.println("Safado");
			return false;
		}
		
		Cliente cliente = (Cliente) obj;
		if ((cliente.getId() != null) && (this.id == cliente.getId())) {
			return true;
		}

		return false;
	}

	@Override	
	public String getString() {
		return super.getString() + ";" + this.nome + ";" + this.CPF + ";" + this.RG;
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
		return IOTools.geradorDeToString( new String[]{id.toString(),nome, CPF},
				new Integer[]{3,12,14});
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
