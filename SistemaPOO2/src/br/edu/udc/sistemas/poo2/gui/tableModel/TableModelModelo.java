package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Modelo;

public class TableModelModelo extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelModelo() {
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
	Modelo modelo = (Modelo) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return modelo.getId();
	case 1:
	    return modelo.getDescricao();
	case 2:
	    return modelo.getMarca().getDescricao();
	}
	return "";
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "Código";
	case 1:
	    return "Descrição";
	case 2:
	    return "Marca";
	}
	return "";
    }

}