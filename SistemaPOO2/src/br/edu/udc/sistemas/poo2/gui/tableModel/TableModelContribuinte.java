package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;

public class TableModelContribuinte extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelContribuinte() {
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
	return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Cliente Cliente = (Cliente) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return Cliente.getId();
	case 1:
		return Cliente.getDataNascimentoString();
	case 2:
		return Cliente.getTelefone();
	case 3:
		return Cliente.getCelular();
	case 4:
		return Cliente.getLogradouro();
	case 5:
		return Cliente.getNumero();
	case 6:
		return Cliente.getBairro();
	case 7:
		return Cliente.getCidade();
	case 8:
		return Cliente.getEstado();
	case 9:
		return Cliente.getCep();
	}
	return ""; 
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "Codigo";
	case 1:
	    return "Data de Nascimento";
	case 2:
	    return "Telefone";
	case 3:
	    return "Celular";
	case 4:
	    return "Logradouro";
	case 5:
	    return "Numero";
	case 6:
	    return "Bairro";
	case 7:
	    return "Cidade";
	case 8:
	    return "Estado";
	case 9:
	    return "CEP";
	}
	return "";
    }

}
