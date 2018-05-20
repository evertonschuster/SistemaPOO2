package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormModelo extends FormBase {

	public FormModelo() throws Exception {
		this.title = "MODELO";

		this.filters = new String[3];
		this.filters[0] = "Id";
		this.filters[1] = "Descricao";
		this.filters[2] = "Marca";

		this.list.loadFromFile("modelo.dat", new Modelo());
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
		
		System.out.println("Selecione uma marca:");
		FormMarca  formMarca = new FormMarca();
		Marca marca = (Marca) formMarca.selectScreen();

		Modelo modelo = new Modelo();
		modelo.setId(id);
		modelo.setDescricao(descricao);
		modelo.setMarca(marca);

		this.list.add(modelo);
		System.out.println("Modelo inserido com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Modelo modelo = (Modelo) this.list.findById(id);
		if (modelo != null) {
			System.out.print("Entre com a Descricao (" + modelo.getDescricao() + "): ");
			String descricao = IOTools.readString();

			if ((descricao != null) && (descricao.length() > 0)) {
				modelo.setDescricao(descricao);
			}

			System.out.println("Selecione uma marca (" + modelo.getMarca().toString() + "):");
			FormMarca formMarca = new FormMarca();
			Marca marca = (Marca) formMarca.selectScreen();
			modelo.setMarca(marca);
		
			this.list.remove(id);
			this.list.add(modelo);
			System.out.println("Modelo alterado com sucesso!");
		} else {
			System.out.println("Modelo nao encontrado!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Modelo removido com sucesso!");
		} else {
			System.out.println("Modelo nao encontrado!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Modelo modelo = new Modelo();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			modelo.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com a Descrição: ");
			modelo.setDescricao(IOTools.readString());
		} else if (option == '2') {
			System.out.println("Selecione uma marca:");
			FormMarca formMarca = new FormMarca();
			Marca marca = (Marca) formMarca.selectScreen();
			modelo.setMarca(marca);
		} else if ((option == 'x') || (option == 'X')) {
			modelo = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(modelo);
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
				Modelo modelo = (Modelo) this.list.findById(id);
				while (modelo == null) {
					System.out.println("Modelo nao encontrado!");
					System.out.println("-------------------");
					for(int i=0; i<this.list.getSize(); i++) {
						System.out.println(vector[i].toString());
						System.out.println("-------------------");
					}			
					System.out.print("Entre com o Id: ");
					id = IOTools.readInteger();
					modelo = (Modelo) this.list.findById(id);
				}
				return modelo;
			} else {
				System.out.println("Nao exitem modelos!");
				IOTools.getch();
			}
			return new Modelo();
		}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("modelo.dat");
	}
}