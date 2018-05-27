package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Contribuinte;

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
	Contribuinte contribuinte = (Contribuinte) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return contribuinte.getId();
	case 1:
		return contribuinte.getDataNascimentoString();
	case 2:
		return contribuinte.getTelefone();
	case 3:
		return contribuinte.getCelular();
	case 4:
		return contribuinte.getLogradouro();
	case 5:
		return contribuinte.getNumero();
	case 6:
		return contribuinte.getBairro();
	case 7:
		return contribuinte.getCidade();
	case 8:
		return contribuinte.getEstado();
	case 9:
		return contribuinte.getCep();
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
