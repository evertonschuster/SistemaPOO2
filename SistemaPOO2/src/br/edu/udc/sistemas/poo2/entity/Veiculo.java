package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Veiculo extends MyObject {
	private String ano;
	private String placa;
	private String chassis;
	private String cor;
	private Modelo Modelo;
	private Cliente Cliente;	
	
	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	
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
		if ((veiculo.getAno() != null) && (this.ano.contains(veiculo.getAno()))) {
			return true;
		}
		
		if ((veiculo.getModelo() != null) && (this.Modelo != null) &&
			    (this.getModelo().getId() == veiculo.getModelo().getId())){
				return true;
			} 
		if ((veiculo.getCliente() != null) && (this.Cliente != null) &&
			    (this.getCliente().getId() == veiculo.getCliente().getId())){
				return true;
			} 
		return false;
	}

	@Override
	public String getString() {
		return super.getString() + ";" + this.ano + ";" + this.chassis + ";" + this.cor + ";" + this.placa + ";" + this.Modelo + ";" + this.Cliente;
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.ano = values[1];
		this.placa = values[2];
		this.chassis = values[3];
		this.cor = values[4];
		this.Modelo = new Modelo();
		this.Modelo.setId(Integer.parseInt(values[5]));
		this.Modelo.setDescricao(values[6]);
		this.Cliente = new Cliente();
		this.Cliente.setId(Integer.parseInt(values[7]));
		this.Cliente.setNome(values[8]);
	}
	
	public String toString() {
		return IOTools.geradorDeToString( new String[]{super.getId().toString(),this.ano.toString(), this.Modelo.getDescricao()},
				new Integer[]{3,12,14} );
	}
	
	public MyObject clone() {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(this.id);
		veiculo.setAno(ano);
		veiculo.setPlaca(placa);
		veiculo.setChassis(chassis);
		veiculo.setCor(cor);
		veiculo.setModelo((Modelo) this.Modelo.clone());
		veiculo.setCliente((Cliente) this.Cliente.clone());
		return veiculo;
	}

	public MyObject newInstance() {
		return new Veiculo();
	}

}
