package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Modelo extends MyObject {
	private String descricao;
	private Marca marca;

	public Modelo() {
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

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if(!(obj instanceof Modelo)) {
			return false;
		}
		
		if (super.equals(obj)) {
			return true;
		}
		Modelo modelo = (Modelo) obj;
		if ((modelo.getId() != null) && (this.getId() == modelo.getId())) {
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
		return IOTools.geradorDeToString( new String[]{id.toString(),descricao, marca.getDescricao()},
				new Integer[]{3,12,14} );
	}

	public MyObject clone() {
		Modelo modelo = new Modelo();
		modelo.setId(this.id);
		modelo.setDescricao(descricao);
		modelo.setMarca((Marca) this.marca.clone());
		return modelo;
	}

	public MyObject newInstance() {
		return new Modelo();
	}

}
