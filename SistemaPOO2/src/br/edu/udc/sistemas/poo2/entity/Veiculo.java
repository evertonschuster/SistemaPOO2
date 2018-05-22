package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;
import java.util.Date;

public class Veiculo extends MyObject {
	private String ano;
	private Long placa;
	private Long chassis;
	private String cor;
	private Modelo Modelo;
	private Cliente Cliente;	
	private Veiculo Veiculo;
	
	
	
	public Modelo getModelo() {
		return Modelo;
	}

	
	public void setModelo(Modelo modelo) {
		Modelo = modelo;
	}	
	
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Long getPlaca() {
		return placa;
	}

	public void setPlaca(Long placa) {
		this.placa = placa;
	}

	public Long getChassis() {
		return chassis;
	}

	public void setChassis(Long chassis) {
		this.chassis = chassis;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return Veiculo;
	}

	public void setVeiculo(Veiculo Veiculo) {
		this.Veiculo = Veiculo;
	}
	
	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		Veiculo Veiculo = (Veiculo) obj;
		if ((Veiculo.getAno() != null) && (this.ano.contains(Veiculo.getAno()))) {
			return true;
		}
		
		if ((Veiculo.getModelo() != null) && (this.Modelo != null) &&
			    (this.getModelo().getId() == Veiculo.getModelo().getId())){
				return true;
			} 
		if ((Veiculo.getCliente() != null) && (this.Cliente != null) &&
			    (this.getCliente().getId() == Veiculo.getCliente().getId())){
				return true;
			} 
		return false;
	}

	@Override
	public String getString() {
		return super.getString() + ";" + this.ano + ";" + Veiculo.getString();
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.cor = values[1];
		this.Veiculo = new Veiculo();
		this.Veiculo.setId(Integer.parseInt(values[2]));
		this.Veiculo.setAno(values[3]);
	}
	
	public String toString() {
		return super.toString() + " - Ano = " + this.ano + " - " + this.Veiculo;
	}

	public MyObject clone() {
		Veiculo Veiculo = new Veiculo();
		Veiculo.setId(this.id);
		Veiculo.setAno(ano);
		Veiculo.setPlaca(placa);
		Veiculo.setChassis(chassis);
		Veiculo.setCor(cor);
		Veiculo.setVeiculo((Veiculo) this.Veiculo.clone());
		return Veiculo;
	}

	public MyObject newInstance() {
		return new Veiculo();
	}

}
