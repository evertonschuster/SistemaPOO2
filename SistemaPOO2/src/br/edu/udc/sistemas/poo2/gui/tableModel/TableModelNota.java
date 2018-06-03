package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Nota;

public class TableModelNota extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected Object list[];

	public TableModelNota() {
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
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Nota nota = (Nota) list[rowIndex];
		switch (columnIndex) {
			case 0:
				return nota.getId();
			case 1:
				return nota.getDescricao();
			case 2:
				return nota.getNumeroNota();
			case 3:
				return nota.getData().toString();
			case 4:
				return nota.getFuncionario().getNome();
			case 5:
				return nota.getTipoNota();

		}
		return "";
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "C�digo";
		case 1:
			return "Descri��o";
		case 2:
			return "Numero Da Nota";
		case 3:
			return "Datas";
		case 4:
			return "Funcionario";
		case 5:
			return "Tipo da Nota";
		}
		return "";
	}

}
