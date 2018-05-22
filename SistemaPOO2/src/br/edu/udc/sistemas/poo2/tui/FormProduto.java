package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormProduto extends FormBase {

	public FormProduto() throws Exception {
		this.title = "Produto";

		this.filters = new String[6];
		this.filters[0] = "Id";
		this.filters[1] = "Descricao";
		this.filters[2] = "Valor";
		this.filters[3] = "QTD";
		this.filters[4] = "QTDMinimo";
		this.filters[5] = "Marca";

		this.list.loadFromFile("Produto.dat", new Produto());
	}

	public void insertScreen() throws Exception {
		super.insertScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		MyObject objFind = this.list.findById(id);
		while (objFind != null) {
			super.insertScreen();
			System.out.println("Id ja existente");
			System.out.print("Entre com o Id: ");
			id = IOTools.readInteger();
			objFind = this.list.findById(id);
		}

		System.out.print("Entre com a Descricao: ");
		String descricao = IOTools.readString();
		
		System.out.print("Entre com a Valor: ");
		Double valor = IOTools.readDouble();
		
		System.out.print("Entre com a Quantidade: ");
		int qtd = IOTools.readInteger();
		
		System.out.print("Entre com a Quantidade Minima: ");
		int qtdMinimo = IOTools.readInteger();
		
		System.out.println("Selecione uma marca:");
		FormMarca  formMarca = new FormMarca();
		Marca marca = (Marca) formMarca.selectScreen();

		Produto Produto = new Produto();
		Produto.setId(id);
		Produto.setDescricao(descricao);
		Produto.setValor(valor);
		Produto.setQtd(qtd);
		Produto.setQtdMinimo(qtdMinimo);
		Produto.setMarca(marca);

		this.list.add(Produto);
		System.out.println("Produto inserido com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Produto Produto = (Produto) this.list.findById(id);
		if (Produto != null) {
			System.out.print("Entre com a Descricao (" + Produto.getDescricao() + "): ");
			String descricao = IOTools.readString();

			if ((descricao != null) && (descricao.length() > 0)) {
				Produto.setDescricao(descricao);
			}
			
			System.out.print("Entre com a Valor (" + Produto.getValor() + "): ");
			Double valor = IOTools.readDouble();

			if ((valor != null)) {
				Produto.setValor(valor);
			}
			
			System.out.print("Entre com a Quantidade (" + Produto.getQtd() + "): ");
			int qtd = IOTools.readInteger();

			//if ((qtd != null)) {
				Produto.setQtd(qtd);
			//}
			
			System.out.print("Entre com a Quantidade Minima (" + Produto.getQtdMinimo() + "): ");
			int qtdMinimo = IOTools.readInteger();

			//if ((qtdMinimo != null)) {
				Produto.setQtdMinimo(qtdMinimo);
			//}

			System.out.println("Selecione uma marca (" + Produto.getMarca().toString() + "):");
			FormMarca formMarca = new FormMarca();
			Marca marca = (Marca) formMarca.selectScreen();
			Produto.setMarca(marca);
		
			this.list.remove(id);
			this.list.add(Produto);
			System.out.println("Produto alterado com sucesso!");
		} else {
			System.out.println("Produto nao encontrado!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Produto removido com sucesso!");
		} else {
			System.out.println("Produto nao encontrado!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Produto Produto = new Produto();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			Produto.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com a Descricao: ");
			Produto.setDescricao(IOTools.readString());
		} else if (option == '2') {
			System.out.print("Entre com a Valor: ");
			Produto.setValor(IOTools.readDouble());
		} else if (option == '3') {
			System.out.print("Entre com a Quantidade: ");
			Produto.setQtd(IOTools.readInteger());
		} else if (option == '4') {
			System.out.print("Entre com a Quatidade Minima: ");
			Produto.setQtdMinimo(IOTools.readInteger());
		} else if (option == '5') {
			System.out.println("Selecione uma marca:");
			FormMarca formMarca = new FormMarca();
			Marca marca = (Marca) formMarca.selectScreen();
			Produto.setMarca(marca);
		} else if ((option == 'x') || (option == 'X')) {
			Produto = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(Produto);
		MyObject vector[] = filteredList.toArray();
		System.out.println("-------------------");
		for (int i = 0; i < filteredList.getSize(); i++) {
			System.out.println(vector[i].toString());
			System.out.println("-------------------");
		}
		if (filteredList.getSize() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}
		IOTools.getch();
	}

	public MyObject selectScreen() throws Exception {
			if (this.list.getSize() > 0) {
				MyObject vector[] = this.list.toArray();
				System.out.println("-------------------");
				for(int i=0; i<this.list.getSize(); i++) {
					System.out.println(vector[i].toString());
					System.out.println("-------------------");
				}			
				System.out.print("Entre com o Id: ");
				int id = IOTools.readInteger();
				Produto Produto = (Produto) this.list.findById(id);
				while (Produto == null) {
					System.out.println("Produto nao encontrado!");
					System.out.println("-------------------");
					for(int i=0; i<this.list.getSize(); i++) {
						System.out.println(vector[i].toString());
						System.out.println("-------------------");
					}			
					System.out.print("Entre com o Id: ");
					id = IOTools.readInteger();
					Produto = (Produto) this.list.findById(id);
				}
				return Produto;
			} else {
				System.out.println("Nao exitem Produtos!");
				IOTools.getch();
			}
			return new Produto();
		}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("Produto.dat");
	}
}