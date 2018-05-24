package br.edu.udc.sistemas.poo2.entity;


import java.util.Date;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Cliente extends MyObject {
	private String nome;
	private String RG;
	private String CPF;
	private String DtNasc;
	private String telf;
	private String celular;
	private String logradoudo;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private Cliente Cliente;
	
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = null;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(String dtNasc) {
		DtNasc = dtNasc;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLogradoudo() {
		return logradoudo;
	}

	public void setLogradoudo(String logradoudo) {
		this.logradoudo = logradoudo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		Cliente Cliente = (Cliente) obj;
		if ((Cliente.getNome() != null) && (this.nome.contains(Cliente.getNome()))) {
			return true;
		}

		
		if ((Cliente.getCliente() != null) && (this.Cliente != null) &&
		    (this.getCliente().getId() == Cliente.getCliente().getId())){
			return true;
		} 
		return false;
	}

	@Override
	
	public String getString() {
		return super.getString() + ";" + this.nome + ";" + Cliente.getString();
	}
	
	@Override
	public void setString(String str) {
		String values[] = str.split(";");
		super.setString(values[0]);
		this.nome = values[1];
		this.RG = values[2];
		this.CPF = values[3];
		this.telf = values[4];
		this.celular = values[5];
		this.DtNasc = values[6];
		this.logradoudo = values[7];
		this.numero = values[8];
		this.bairro = values[9];
		this.cidade = values[10];
		this.estado = values[11];
		this.cep = values[12];
		this.Cliente = new Cliente();
		this.Cliente.setId(Integer.parseInt(values[6]));
		this.Cliente.setNome(values[7]);
	}
	
	public String toString() {
		return super.toString() + " - Nome = " + this.nome + " - " + this.Cliente;
	}

	public MyObject clone() {
		Cliente Cliente = new Cliente();
		Cliente.setId(this.id);
		Cliente.setNome(nome);
		Cliente.setCPF(CPF);
		Cliente.setRG(RG);
		Cliente.setDtNasc(DtNasc);
		Cliente.setTelf(telf);
		Cliente.setCelular(celular);
		Cliente.setLogradoudo(logradoudo);
		Cliente.setNumero(numero);
		Cliente.setBairro(bairro);
		Cliente.setCidade(cidade);
		Cliente.setEstado(estado);
		Cliente.setCep(cep);
		Cliente.setCliente((Cliente) this.Cliente.clone());
		return Cliente;
	}

	public MyObject newInstance() {
		return new Cliente();
	}

}
