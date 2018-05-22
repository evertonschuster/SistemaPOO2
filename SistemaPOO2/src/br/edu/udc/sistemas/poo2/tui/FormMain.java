package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.infra.IOTools;

public class FormMain extends Form {

	public FormMain() {
		this.title = "MENU PRINCIPAL";

		this.options = new String[10];

		this.options[0] = "MARCA";
		this.options[1] = "MODELO";
		this.options[2] = "VEICULO";
		this.options[3] = "CLIENTE";
		this.options[4] = "FORNECEDOR";
		this.options[5] = "PRODUTO";
		this.options[6] = "SERVICO";
		this.options[7] = "FUNCIONARIO";
		this.options[8] = "COMPRA";
		this.options[9] = "VENDA";
	}

	@Override
	public void selectOption(char option) throws Exception {
		FormMarca frmMarca = null;
		FormModelo frmModelo = null;
		FormCliente frmCliente = null;
		FormVeiculo frmVeiculo = null;
		FormProduto frmProduto = null;
		FormServico frmServico = null;
		switch (option) {
		case '0':
			frmMarca = new FormMarca();
			frmMarca.execute();
			break;
		case '1':
			frmModelo = new FormModelo();
			frmModelo.execute();
			break;
		case '2':
			frmCliente = new FormCliente();
			frmCliente.execute();
			break;
		case '3':
			frmVeiculo = new FormVeiculo();
			frmVeiculo.execute();
			break;
		case '4':
			frmProduto = new FormProduto();
			frmProduto.execute();
			break;
		case '5':
			frmServico = new FormServico();
			frmServico.execute();
			break;
		case '6':
			System.out.println("Fornecedor");
			IOTools.getch();
			break;
		case '7':
			System.out.println("Funcionario");
			IOTools.getch();
			break;
		case '8':
			System.out.println("Compra");
			IOTools.getch();
			break;
		case '9':
			System.out.println("Venda");
			IOTools.getch();
			break;
		default:
			System.out.println("Opcao invalida!");
			IOTools.getch();
			break;
		}
	}
}