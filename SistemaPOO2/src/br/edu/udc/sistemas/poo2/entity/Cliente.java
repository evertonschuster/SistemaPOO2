package br.edu.udc.sistemas.poo2.entity;


import java.util.Date;
import br.edu.udc.sistemas.poo2.infra.MyObject;

public class Cliente extends MyObject {
	private String nome;
	private Long RG;
	private Long CPF;
	private Date DtNasc;
	private Integer telf;
	private Integer celular;
	private String logradoudo;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
	private Integer cep;
	private Cliente Cliente;
	
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = null;
	}

	public Long getRG() {
		return RG;
	}

	public void setRG(Long rG) {
		RG = rG;
	}

	public Long getCPF() {
		return CPF;
	}

	public void setCPF(Long cPF) {
		CPF = cPF;
	}

	public Date getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		DtNasc = dtNasc;
	}

	public Integer getTelf() {
		return telf;
	}

	public void setTelf(Integer telf) {
		this.telf = telf;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getLogradoudo() {
		return logradoudo;
	}

	public void setLogradoudo(String logradoudo) {
		this.logradoudo = logradoudo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
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
		this.logradoudo = values[2];
		this.bairro = values[3];
		this.cidade = values[4];
		this.estado = values[5];
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
