package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.NotaCompra;

public class TableModelNotaCompta extends TableModelNota {

	public TableModelNotaCompta() {
		super();
	}
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		NotaCompra nota = (NotaCompra) list[rowIndex];
		switch (columnIndex) {
			case 6:
				return (nota.getFornecedor() != null ?nota.getFornecedor().getNomeFantazia() : " ");
		}
		return super.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 6:
			return "Fornecedor";
		}
		return super.getColumnName(column);
	}

}
