package br.edu.udc.sistemas.poo2.entity;

public class NotaVenda extends Nota {
	protected Cliente cliente;
	protected Veiculo veiculo;

	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public NotaVenda() {
		super();
		this.tipoNota = "venda";
	}
}
