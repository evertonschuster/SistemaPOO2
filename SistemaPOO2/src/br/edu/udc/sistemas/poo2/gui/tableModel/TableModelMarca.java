package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Marca;

public class TableModelMarca extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Object list[];

	public TableModelMarca() {
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
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Marca marca = (Marca) list[rowIndex];
		switch (columnIndex) {
			case 0:
				return marca.getId();
			case 1:
				return marca.getDescricao();
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
		}
		return "";
	}

}
