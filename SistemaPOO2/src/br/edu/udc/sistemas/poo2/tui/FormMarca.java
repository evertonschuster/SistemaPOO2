package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormMarca extends FormBase {

	public FormMarca() throws Exception {
		this.title = "MARCA";

		this.filters = new String[2];
		this.filters[0] = "Id";
		this.filters[1] = "Descricao";

		this.list.loadFromFile("marca.dat", new Marca());
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

		System.out.print("Entre com a Descrição: ");
		String descricao = IOTools.readString();

		Marca marca = new Marca();
		marca.setId(id);
		marca.setDescricao(descricao);

		this.list.add(marca);
		System.out.println("Marca inserida com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Marca marca = (Marca) this.list.findById(id);
		if (marca != null) {
			System.out.print("Entre com a Descricao (" + marca.getDescricao() + "): ");
			String descricao = IOTools.readString();

			if ((descricao != null) && (descricao.length() > 0)) {
				marca.setDescricao(descricao);
			}

			this.list.remove(id);
			this.list.add(marca);
			System.out.println("Marca alterada com sucesso!");
		} else {
			System.out.println("Marca nao encontrada!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Marca removida com sucesso!");
		} else {
			System.out.println("Marca nao encontrada!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Marca marca = new Marca();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			marca.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com a Descrição: ");
			marca.setDescricao(IOTools.readString());
		} else if ((option == 'x') || (option == 'X')) {
			marca = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(marca);
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
			for (int i = 0; i < this.list.getSize(); i++) {
				System.out.println(vector[i].toString());
				System.out.println("-------------------");
			}
			System.out.print("Entre com o Id: ");
			int id = IOTools.readInteger();
			Marca marca = (Marca) this.list.findById(id);
			while (marca == null) {
				System.out.println("Marca nao encontrada!");
				System.out.println("-------------------");
				for (int i = 0; i < this.list.getSize(); i++) {
					System.out.println(vector[i].toString());
					System.out.println("-------------------");
				}
				System.out.print("Entre com o Id: ");
				id = IOTools.readInteger();
				marca = (Marca) this.list.findById(id);
			}
			return marca;
		} else {
			System.out.println("Nao exitem marcas!");
			IOTools.getch();
		}
		return new Marca();
	}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("marca.dat");
	}
}