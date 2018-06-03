package br.edu.udc.sistemas.poo2.entity;

public class NotaCompra extends Nota {

	protected Fornecedor fornecedor;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public NotaCompra() {
		super();
		this.tipoNota = "Compra";
	}
	
}
