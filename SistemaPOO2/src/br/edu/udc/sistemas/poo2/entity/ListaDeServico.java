package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class ListaDeServico extends MyObject {
	protected Nota nota;
	protected Servico servico;
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if(!(obj instanceof ListaDeServico)) {
			System.out.println("Safado");
			return false;
		}

		ListaDeServico lista = (ListaDeServico) obj; 
		
		return this.getNota().equals(lista.getNota()) && this.getServico().equals(lista.getServico()); 
	}


	public String toString() {
		return this.getNota().getId() + " | " + this.getServico().getDescricao();
	}

	public MyObject clone() {
		ListaDeServico listaDeServico = new ListaDeServico();
		listaDeServico.setNota(this.nota);
		listaDeServico.setServico(this.servico);

		return listaDeServico;
	}

	public MyObject newInstance() {
		return new ListaDeServico();
	}

}
