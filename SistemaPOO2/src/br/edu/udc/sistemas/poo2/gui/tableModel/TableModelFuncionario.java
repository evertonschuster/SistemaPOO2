package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Funcionario;

public class TableModelFuncionario extends TableModelCliente {

	private static final long serialVersionUID = 1L;

    //private Object list[];

    public TableModelFuncionario() {
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
	return super.getColumnCount() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Funcionario funcionario = (Funcionario) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return funcionario.getId();
	case 1:
	    return funcionario.getLogin();
	}

	return super.getValueAt(rowIndex, columnIndex - 1); 
	
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "Codigo";
	case 1:
	    return "Login";
	}
	
	return super.getColumnName(column -1);
    }

}