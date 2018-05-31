package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Servico;

public class TableModelServico extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Object list[];

	public TableModelServico() {
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
		Servico Servico = (Servico) list[rowIndex];
		switch (columnIndex) {
			case 0:
				return Servico.getId();
			case 1:
				return Servico.getDescricao();
			case 2:
				return Servico.getValor();
		}
		return "";
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Codigo";
		case 1:
			return "Descricao";
		case 2:
			return "Valor";
		}
		return "";
	}

}
