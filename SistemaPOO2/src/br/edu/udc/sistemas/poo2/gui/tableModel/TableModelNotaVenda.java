package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.NotaCompra;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;

public class TableModelNotaVenda extends TableModelNota {
	public TableModelNotaVenda() {
		super();
	}
	
	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		NotaVenda nota = (NotaVenda) list[rowIndex];
		switch (columnIndex) {
			case 6:
				return (nota.getCliente() != null ? nota.getCliente().getNome() : " ");
			case 7:
				return (nota.getVeiculo() != null  && nota.getVeiculo().getModelo() != null ? nota.getVeiculo().getModelo().getDescricao() : " "); 
		}
		return super.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 6:
			return "Cliente";
		case 7:
			return "Veiculo";
		}
		return super.getColumnName(column);
	}
}
