package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormBase extends Form {

	protected List list;
	protected String filters[];

	public FormBase() {
		this.list = new List();
		this.title = null;

		this.filters = null;

		this.options = new String[4];

		this.options[0] = "INSERIR";
		this.options[1] = "ALTERAR";
		this.options[2] = "EXCLUIR";
		this.options[3] = "CONSULTAR";
	}

	@Override
	public void selectOption(char option) throws Exception {
		switch (option) {
		case '0':
			insertScreen();
			break;
		case '1':
			updateScreen();
			break;
		case '2':
			removeScreen();
			break;
		case '3':
			findScreen();
			break;
		default:
			System.out.println("Opcao invalida!");
			IOTools.getch();
			break;
		}
	}

	public char showFilters() throws Exception {
		System.out.println("--------------------");
		if (this.title != null) {
			System.out.println("| Filtros");
		}
		System.out.println("--------------------");

		for (int i = 0; i < this.filters.length; i++) {
			System.out.println("|" + i + ". " + this.filters[i]);
		}
		System.out.println("|X. Nenhum");
		System.out.println("--------------------");
		System.out.println("Entre com uma opcao: ");
		return IOTools.getche();
	}

	public void insertScreen() throws Exception {
		IOTools.clrscr();
		System.out.println("--------------------");
		System.out.println("| INSERIR");
		System.out.println("--------------------");
	}

	public void updateScreen() throws Exception {
		IOTools.clrscr();
		System.out.println("--------------------");
		System.out.println("| ALTERAR");
		System.out.println("--------------------");
	}

	public void removeScreen() throws Exception {
		IOTools.clrscr();
		System.out.println("--------------------");
		System.out.println("| EXCLUIR");
		System.out.println("--------------------");
	}

	public void findScreen() throws Exception {
		IOTools.clrscr();
		System.out.println("--------------------");
		System.out.println("| CONSULTAR");
		System.out.println("--------------------");
	}

	public MyObject selectScreen() throws Exception {
		System.out.println("--------------------");
		System.out.println("| SELECIONAR " + this.title);
		System.out.println("--------------------");
		return null;
	}
}
