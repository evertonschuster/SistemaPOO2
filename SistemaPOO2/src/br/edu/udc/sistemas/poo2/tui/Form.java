package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Form extends MyObject {

	protected String options[];
	protected String title;

	public Form() {
		this.options = null;
		this.title = null;
	}

	public String[] getOptions() {
		return this.options;
	}

	public void setOptions(String options[]) {
		this.options = options;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void show() {
		IOTools.clrscr();
		System.out.println("--------------------");
		if (this.title != null) {
			System.out.println("| " + this.title);
		}
		System.out.println("--------------------");

		for (int i = 0; i < this.options.length; i++) {
			System.out.println("|" + i + " . " + this.options[i]);
		}
		System.out.println("--------------------");
		System.out.println("|X. Sair");
		System.out.println("--------------------");
	}

	public void selectOption(char option) throws Exception {

	}
	
	public void destroy() throws Exception {
		
	}

	public void execute() throws Exception {
		show();
		System.out.print("Entre com uma opcao: ");
		char option = IOTools.getche();
		while ((option != 'x') && (option != 'X')) {
			try {
				this.selectOption(option);
			} catch(Exception e) {
				System.out.println("Ocorreu um erro: ");
				e.printStackTrace();
				IOTools.getch();
			}
			show();
			System.out.print("Entre com uma opcao: ");
			option = IOTools.getche();
		}
		this.destroy();
	}
}
