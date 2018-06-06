package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;

public class TableModelCliente extends TableModelContribuinte {

    private static final long serialVersionUID = 1L;

    @Override
    public int getColumnCount() {
	return super.getColumnCount() + 4;
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

	return super.getValueAt(rowIndex, columnIndex - 4); 
	
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
	    return "CPF";
	}
	
	return super.getColumnName(column -4);
    }

}