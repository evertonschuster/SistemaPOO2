package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;

public class TableModelCliente extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelCliente() {
	this.list = new Object[0];
    }

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
        this.list = list;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
	return this.list.length;
    }

    @Override
    public int getColumnCount() {
	return 13;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Cliente cliente = (Cliente) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return cliente.getId();
	case 1:
	    return cliente.getNome();
	case 2:
		return cliente.getRG();
	case 3:
		return cliente.getCPF();
	case 4:
		return cliente.getDataNascimentoString();
	case 5:
		return cliente.getTelefone();
	case 6:
		return cliente.getCelular();
	case 7:
		return cliente.getLogradouro();
	case 8:
		return cliente.getNumero();
	case 9:
		return cliente.getBairro();
	case 10:
		return cliente.getCidade();
	case 11:
		return cliente.getEstado();
	case 12:
		return cliente.getCep();
	}
	return ""; 
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "Codigo";
	case 1:
	    return "Nome";
	case 2:
	    return "RG";
	case 3:
	    return "cpf";
	case 4:
	    return "Data de Nascimento";
	case 5:
	    return "Telefone";
	case 6:
	    return "Celular";
	case 7:
	    return "Logradouro";
	case 8:
	    return "Numero";
	case 9:
	    return "Bairro";
	case 10:
	    return "Cidade";
	case 11:
	    return "Estado";
	case 12:
	    return "CEP";
	}
	return "";
    }

}