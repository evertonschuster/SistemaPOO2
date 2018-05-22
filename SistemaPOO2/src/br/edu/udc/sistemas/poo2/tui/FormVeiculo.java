package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormVeiculo extends FormBase {

	public FormVeiculo() throws Exception {
		this.title = "Veiculo";

		this.filters = new String[4];
		this.filters[0] = "Id";
		this.filters[1] = "Ano";
		this.filters[2] = "Placa";
		this.filters[3] = "Chassis";
		this.filters[4] = "Cor";
		this.filters[5] = "Modelo";
		this.filters[6] = "Cliente";

		this.list.loadFromFile("Veiculo.dat", new Veiculo());
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

		System.out.print("Entre com a Ano: ");
		String ano = IOTools.readString();
		
		System.out.print("Entre com a Placa: ");
		String placa = IOTools.readString();
		
		System.out.print("Entre com a Chassis: ");
		String chassis = IOTools.readString();
		
		System.out.print("Entre com a Cor: ");
		String cor = IOTools.readString();
		
		System.out.println("Selecione uma Modelo:");
		FormModelo  formModelo = new FormModelo();
		Modelo Modelo = (Modelo) formModelo.selectScreen();
		
		System.out.println("Selecione um Cliente:");
		FormCliente  formCliente = new FormCliente();
		Cliente Cliente = (Cliente) formCliente.selectScreen();

		Veiculo Veiculo = new Veiculo();
		Veiculo.setId(id);
		Veiculo.setAno(ano);
		Veiculo.setPlaca(placa);
		Veiculo.setChassis(chassis);
		Veiculo.setCor(cor);
		Veiculo.setModelo(Modelo);
		Veiculo.setCliente(Cliente);
		
		this.list.add(Veiculo);
		System.out.println("Veiculo inserido com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Veiculo Veiculo = (Veiculo) this.list.findById(id);
		if (Veiculo != null) {
			System.out.print("Entre com a Ano (" + Veiculo.getAno() + "): ");
			String ano = IOTools.readString();

			if ((ano != null) && (ano.length() > 0)) {
				Veiculo.setAno(ano);
			}
			
			System.out.print("Entre com a Placa (" + Veiculo.getPlaca() + "): ");
			String placa = IOTools.readString();

			if ((placa != null)) {
				Veiculo.setPlaca(placa);
			}
			
			System.out.print("Entre com a Chassis (" + Veiculo.getChassis() + "): ");
			String chassis = IOTools.readString();

			if ((chassis != null)) {
				Veiculo.setChassis(chassis);
			}
			
			System.out.print("Entre com a Cor (" + Veiculo.getCor() + "): ");
			String cor = IOTools.readString();

			if ((cor != null) && (cor.length() > 0)) {
				Veiculo.setAno(cor);
			}

			System.out.println("Selecione uma Modelo (" + Veiculo.getModelo().toString() + "):");
			FormModelo formModelo = new FormModelo();
			Modelo Modelo = (Modelo) formModelo.selectScreen();
			Veiculo.setModelo(Modelo);
			
			System.out.println("Selecione uma Cliente (" + Veiculo.getCliente().toString() + "):");
			FormCliente formCliente = new FormCliente();
			Cliente Cliente = (Cliente) formCliente.selectScreen();
			Veiculo.setCliente(Cliente);
		
			this.list.remove(id);
			this.list.add(Veiculo);
			System.out.println("Veiculo alterado com sucesso!");
		} else {
			System.out.println("Veiculo nao encontrado!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Veiculo removido com sucesso!");
		} else {
			System.out.println("Veiculo nao encontrado!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Veiculo Veiculo = new Veiculo();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			Veiculo.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com a Ano: ");
			Veiculo.setAno(IOTools.readString());
		} else if (option == '2') {
			System.out.print("Entre com a Placa: ");
			Veiculo.setPlaca(IOTools.readString());
		} else if (option == '3') {
			System.out.print("Entre com a Chassis: ");
			Veiculo.setChassis(IOTools.readString());
		} else if (option == '4') {
			System.out.print("Entre com a Cor: ");
			Veiculo.setCor(IOTools.readString());
		} else if (option == '5') {
			System.out.println("Selecione uma Modelo:");
			FormModelo formModelo = new FormModelo();
			Modelo Modelo = (Modelo) formModelo.selectScreen();
			Veiculo.setModelo(Modelo);
			
		} else if (option == '6') {
			System.out.println("Selecione um Cliente:");
			FormCliente formCliente = new FormCliente();
			Cliente Cliente = (Cliente) formCliente.selectScreen();
			Veiculo.setCliente(Cliente);
			
		} else if ((option == 'x') || (option == 'X')) {
			Veiculo = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(Veiculo);
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
				Veiculo Veiculo = (Veiculo) this.list.findById(id);
				while (Veiculo == null) {
					System.out.println("Veiculo nao encontrado!");
					System.out.println("-------------------");
					for(int i=0; i<this.list.getSize(); i++) {
						System.out.println(vector[i].toString());
						System.out.println("-------------------");
					}			
					System.out.print("Entre com o Id: ");
					id = IOTools.readInteger();
					Veiculo = (Veiculo) this.list.findById(id);
				}
				return Veiculo;
			} else {
				System.out.println("Nao exitem Veiculos!");
				IOTools.getch();
			}
			return new Veiculo();
		}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("Veiculo.dat");
	}
}