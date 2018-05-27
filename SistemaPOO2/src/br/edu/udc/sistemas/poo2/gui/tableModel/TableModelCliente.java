package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;

public class TableModelCliente extends TableModelContribuinte {

    private static final long serialVersionUID = 1L;

    //private Object list[];

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
	return super.getColumnCount() + 3;
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
	}

	return super.getValueAt(rowIndex, columnIndex - 3); 
	
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
	}
	
	return super.getColumnName(column -3);
    }

}