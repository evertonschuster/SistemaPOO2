package br.edu.udc.sistemas.poo2.entity;

import br.edu.udc.sistemas.poo2.infra.MyObject;

public class ListaDeProduto extends MyObject{

	protected Nota nota;
	protected Integer qnt;
	protected Produto produto;
	
	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Nota getNota() {
		return nota;
	}


	public void setNota(Nota nota) {
		this.nota = nota;
	}


	public Integer getQnt() {
		return qnt;
	}


	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}


	public ListaDeProduto() {

	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if(!(obj instanceof ListaDeProduto)) {
			System.out.println("Safado");
			return false;
		}

		ListaDeProduto lista = (ListaDeProduto) obj; 
		//se a nosta deste objeto estiver null, significa que o pedido veio apartir de grid TableModelListaDeServiço, e portanto, e provavel q ainda n tenha nota salva
		if(this.getNota() == null ){
			return this.getProduto().equals(lista.getProduto());
		}
		
		return this.getNota().equals(lista.getNota()) && this.getProduto().equals(lista.getProduto()); 
	}


	public String toString() {
		return this.getNota().getId() + " | " + this.getProduto().getDescricao();
	}

	public MyObject clone() {
		ListaDeProduto listaDeProduto = new ListaDeProduto();
		listaDeProduto.setNota(this.nota);
		listaDeProduto.setProduto(this.produto);
		listaDeProduto.setQnt(this.qnt);

		return listaDeProduto;
	}

	public MyObject newInstance() {
		return new ListaDeProduto();
	}
}
