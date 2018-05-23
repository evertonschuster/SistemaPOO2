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
	return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Cliente Cliente = (Cliente) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return Cliente.getId();
	case 1:
	    return Cliente.getNome();
	case 2:
		return Cliente.getRG();
	case 3:
		return Cliente.getCPF();
	case 4:
		return Cliente.getDtNasc();
	case 5:
		return Cliente.getTelf();
	case 6:
		return Cliente.getCelular();
	case 7:
		return Cliente.getLogradoudo();
	case 8:
		return Cliente.getNumero();
	case 9:
		return Cliente.getBairro();
	case 10:
		return Cliente.getCidade();
	case 11:
		return Cliente.getEstado();
	case 12:
		return Cliente.getCep();
	}
	return ""; 
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "codigo";
	case 1:
	    return "nome";
	case 2:
	    return "rg";
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