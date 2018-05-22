package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormCliente extends FormBase {

	public FormCliente() throws Exception {
		this.title = "Cliente";

		this.filters = new String[13];
		this.filters[0] = "Id";
		this.filters[1] = "Nome";
		this.filters[3] = "RG";
		this.filters[4] = "CPF";
		this.filters[5] = "DtNasc";
		this.filters[6] = "Tel";
		this.filters[7] = "Celular";
		this.filters[8] = "Logradouro";
		this.filters[9] = "Numero";
		this.filters[10] = "Bairro";
		this.filters[11] = "Cidade";
		this.filters[12] = "Estado";
		this.filters[13] = "CEP";

		this.list.loadFromFile("Cliente.dat", new Cliente());
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

		System.out.print("Entre com o Nome: ");
		String nome = IOTools.readString();
		
		Cliente Cliente = new Cliente();
		Cliente.setId(id);
		Cliente.setNome(nome);
		

		this.list.add(Cliente);
		System.out.println("Cliente inserido com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Cliente Cliente = (Cliente) this.list.findById(id);
		if (Cliente != null) {
			System.out.print("Entre com o Nome (" + Cliente.getNome() + "): ");
			String nome = IOTools.readString();

			if ((nome != null) && (nome.length() > 0)) {
				Cliente.setNome(nome);
			}
		
			this.list.remove(id);
			this.list.add(Cliente);
			System.out.println("Cliente alterado com sucesso!");
		} else {
			System.out.println("Cliente nao encontrado!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Cliente removido com sucesso!");
		} else {
			System.out.println("Cliente nao encontrado!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Cliente Cliente = new Cliente();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			Cliente.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com o Nome: ");
			Cliente.setNome(IOTools.readString());
		} else if ((option == 'x') || (option == 'X')) {
			Cliente = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(Cliente);
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
				Cliente Cliente = (Cliente) this.list.findById(id);
				while (Cliente == null) {
					System.out.println("Cliente nao encontrado!");
					System.out.println("-------------------");
					for(int i=0; i<this.list.getSize(); i++) {
						System.out.println(vector[i].toString());
						System.out.println("-------------------");
					}			
					System.out.print("Entre com o Id: ");
					id = IOTools.readInteger();
					Cliente = (Cliente) this.list.findById(id);
				}
				return Cliente;
			} else {
				System.out.println("Nao exitem Clientes!");
				IOTools.getch();
			}
			return new Cliente();
		}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("Cliente.dat");
	}
}