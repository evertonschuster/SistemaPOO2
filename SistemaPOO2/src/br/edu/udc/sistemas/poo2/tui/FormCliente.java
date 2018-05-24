package br.edu.udc.sistemas.poo2.tui;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.infra.List;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class FormCliente extends FormBase {

	public FormCliente() throws Exception {
		this.title = "Cliente";

		this.filters = new String[13];
		this.filters[0] = "id";
		this.filters[1] = "nome";
		this.filters[3] = "rg";
		this.filters[4] = "cpf";
		this.filters[5] = "dtnasc";
		this.filters[6] = "telf";
		this.filters[7] = "celular";
		this.filters[8] = "logradouro";
		this.filters[9] = "numero";
		this.filters[10] = "bairro";
		this.filters[11] = "cidade";
		this.filters[12] = "estado";
		this.filters[13] = "cep";

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
		System.out.print("Entre com o CPF: ");
		String cpf = IOTools.readString();
		System.out.print("Entre com o RG: ");
		String rg = IOTools.readString();
		System.out.print("Entre com o Data de Nascimento: ");
		String dtNasc = IOTools.readString();
		System.out.print("Entre com o Telefone: ");
		String telf = IOTools.readString();
		System.out.print("Entre com o Celular: ");
		String celular = IOTools.readString();
		System.out.print("Entre com o Logradouro: ");
		String logradouro = IOTools.readString();
		System.out.print("Entre com o Numero: ");
		String numero = IOTools.readString();
		System.out.print("Entre com o Bairro: ");
		String bairro = IOTools.readString();
		System.out.print("Entre com o Cidade: ");
		String cidade = IOTools.readString();
		System.out.print("Entre com o Estado: ");
		String estado = IOTools.readString();
		System.out.print("Entre com o CEP: ");
		String cep = IOTools.readString();
		
		Cliente Cliente = new Cliente();
		Cliente.setId(id);
		Cliente.setNome(nome);
		Cliente.setCPF(cpf);
		Cliente.setRG(rg);
		Cliente.setDtNasc(dtNasc);
		Cliente.setTelf(telf);
		Cliente.setCelular(celular);
		Cliente.setLogradoudo(logradouro);
		Cliente.setNumero(numero);
		Cliente.setBairro(bairro);
		Cliente.setCidade(cidade);
		Cliente.setEstado(estado);
		Cliente.setCep(cep);
		

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
			
			System.out.print("Entre com o RG (" + Cliente.getRG() + "): ");
			String rg = IOTools.readString();

			if ((rg != null) && (rg.length() > 0)) {
				Cliente.setRG(rg);
			}

			System.out.print("Entre com o CPF (" + Cliente.getCPF() + "): ");
			String cpf = IOTools.readString();

			if ((cpf != null) && (cpf.length() > 0)) {
				Cliente.setCPF(cpf);
			}
			
			System.out.print("Entre com o Data Nascimento (" + Cliente.getDtNasc() + "): ");
			String dtNasc = IOTools.readString();

			if ((dtNasc != null) && (dtNasc.length() > 0)) {
				Cliente.setDtNasc(dtNasc);
			}
			
			System.out.print("Entre com o Telefone (" + Cliente.getTelf() + "): ");
			String telf = IOTools.readString();

			if ((telf != null) && (telf.length() > 0)) {
				Cliente.setTelf(telf);
			}
			
			System.out.print("Entre com o Celular (" + Cliente.getCelular() + "): ");
			String celular = IOTools.readString();

			if ((celular != null) && (celular.length() > 0)) {
				Cliente.setNome(celular);
			}
			
			System.out.print("Entre com o Logradouro (" + Cliente.getLogradoudo() + "): ");
			String logradoro = IOTools.readString();

			if ((logradoro != null) && (logradoro.length() > 0)) {
				Cliente.setLogradoudo(logradoro);
			}
			
			System.out.print("Entre com o Numero (" + Cliente.getNumero() + "): ");
			String numero = IOTools.readString();

			if ((numero != null) && (numero.length() > 0)) {
				Cliente.setNumero(numero);
			}
			
			System.out.print("Entre com o Bairro (" + Cliente.getBairro() + "): ");
			String bairro = IOTools.readString();

			if ((bairro != null) && (bairro.length() > 0)) {
				Cliente.setBairro(bairro);
			}
			
			System.out.print("Entre com o Cidade (" + Cliente.getCidade() + "): ");
			String cidade = IOTools.readString();

			if ((cidade != null) && (cidade.length() > 0)) {
				Cliente.setCidade(cidade);
			}
			
			System.out.print("Entre com o Estado (" + Cliente.getEstado() + "): ");
			String estado = IOTools.readString();

			if ((estado != null) && (estado.length() > 0)) {
				Cliente.setEstado(estado);
			}
			
			System.out.print("Entre com o CEP (" + Cliente.getCep() + "): ");
			String cep = IOTools.readString();

			if ((cep != null) && (cep.length() > 0)) {
				Cliente.setCep(cep);
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
		long option = this.showFilters();

		Cliente Cliente = new Cliente();
		if (option == '0') {
			System.out.print("Entre com o Id: ");
			Cliente.setId(IOTools.readInteger());
		} else if (option == '1') {
			System.out.print("Entre com o Nome: ");
			Cliente.setNome(IOTools.readString());
		} else if (option == '2') {
			System.out.print("Entre com o RG: ");
			Cliente.setRG(IOTools.readString());
		} else if (option == '3') {
			System.out.print("Entre com o CPF: ");
			Cliente.setCPF(IOTools.readString());
		} else if (option == '4') {
			System.out.print("Entre com o Data de Nascimento: ");
			Cliente.setDtNasc(IOTools.readString());
		} else if (option == '5') {
			System.out.print("Entre com o Logradouro: ");
			Cliente.setLogradoudo(IOTools.readString());
		} else if (option == '6') {
			System.out.print("Entre com o Numero: ");
			Cliente.setNumero(IOTools.readString());
		} else if (option == '7') {
			System.out.print("Entre com o Cidade: ");
			Cliente.setCidade(IOTools.readString());
		} else if (option == '8') {
			System.out.print("Entre com o Estado: ");
			Cliente.setEstado(IOTools.readString());
		} else if (option == '9') {
			System.out.print("Entre com o CEP: ");
			Cliente.setCep(IOTools.readString());
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