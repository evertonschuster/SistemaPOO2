package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormServico extends FormBase {

	public FormServico() throws Exception {
		this.title = "Servico";

		this.filters = new String[3];
		this.filters[0] = "Id";
		this.filters[1] = "Descricao";
		this.filters[2] = "Valor";
		
		this.list.loadFromFile("Servico.dat", new Servico());
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

		Servico Servico = new Servico();
		Servico.setId(id);
		Servico.setDescricao(descricao);
		Servico.setValor(valor);

		this.list.add(Servico);
		System.out.println("Servico inserida com sucesso!");
		IOTools.getch();
	}

	public void updateScreen() throws Exception {
		super.updateScreen();
		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		Servico Servico = (Servico) this.list.findById(id);
		if (Servico != null) {
			System.out.print("Entre com a Descricao (" + Servico.getDescricao() + "): ");
			String descricao = IOTools.readString();

			if ((descricao != null) && (descricao.length() > 0)) {
				Servico.setDescricao(descricao);
			}
			
			System.out.print("Entre com a Valor (" + Servico.getValor() + "): ");
			Double valor = IOTools.readDouble();

			if ((valor != null)) {
				Servico.setValor(valor);
			}

			this.list.remove(id);
			this.list.add(Servico);
			System.out.println("Servico alterada com sucesso!");
		} else {
			System.out.println("Servico nao encontrada!");
		}
		IOTools.getch();
	}

	public void removeScreen() throws Exception {
		super.removeScreen();

		System.out.print("Entre com o Id: ");
		int id = IOTools.readInteger();

		if (this.list.remove(id)) {
			System.out.println("Servico removida com sucesso!");
		} else {
			System.out.println("Servico nao encontrada!");
		}

		IOTools.getch();
	}

	public void findScreen() throws Exception {
		super.findScreen();
		char option = this.showFilters();

		Servico Servico = new Servico();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			Servico.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com a Descricao: ");
			Servico.setDescricao(IOTools.readString());
		} else if (option == '2') {
			System.out.print("Entre com a Valor: ");
			Servico.setValor(IOTools.readDouble());	
		} else if ((option == 'x') || (option == 'X')) {
			Servico = null;
		} else {
			System.out.println("Filtro invalido!");
		}

		List filteredList = this.list.find(Servico);
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
			Servico Servico = (Servico) this.list.findById(id);
			while (Servico == null) {
				System.out.println("Servico nao encontrada!");
				System.out.println("-------------------");
				for (int i = 0; i < this.list.getSize(); i++) {
					System.out.println(vector[i].toString());
					System.out.println("-------------------");
				}
				System.out.print("Entre com o Id: ");
				id = IOTools.readInteger();
				Servico = (Servico) this.list.findById(id);
			}
			return Servico;
		} else {
			System.out.println("Nao exitem Servicos!");
			IOTools.getch();
		}
		return new Servico();
	}
	
	@Override
	public void destroy() throws Exception {
		this.list.saveToFile("Servico.dat");
	}
}