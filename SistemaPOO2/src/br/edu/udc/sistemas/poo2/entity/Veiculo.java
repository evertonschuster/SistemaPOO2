package br.edu.udc.sistemas.poo2.entity;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;


import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Veiculo extends MyObject {
	private String ano;
	private String placa;
	private String chassis;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
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
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		
		if(!(obj instanceof Veiculo)) {
			return false;
		}
		
		Veiculo veiculo = (Veiculo) obj;
		if ((veiculo.getAno() != null) && (this.ano.contains(Veiculo.getAno()))) {
			return true;
		}
		
		if ((veiculo.getModelo() != null) && (this.Modelo != null) &&
			    (this.getModelo().getId() == Veiculo.getModelo().getId())){
				return true;
			} 
		if ((veiculo.getCliente() != null) && (this.Cliente != null) &&
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
		this.ano = values[1];
		this.placa = values[2];
		this.chassis = values[3];
		this.cor = values[4];
		this.Veiculo = new Veiculo();
		this.Veiculo.setId(Integer.parseInt(values[5]));
		this.Veiculo.setAno(values[6]);
	}
	
	public String toString() {
		return super.getId() + "  |  " + this.ano +   "  |  " + this.Veiculo;
	}
	
	public MyObject clone() {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(this.id);
		veiculo.setAno(ano);
		veiculo.setPlaca(placa);
		veiculo.setChassis(chassis);
		veiculo.setCor(cor);
		veiculo.setVeiculo((Veiculo) this.Veiculo.clone());
		return veiculo;
	}

	public MyObject newInstance() {
		return new Veiculo();
	}

}
