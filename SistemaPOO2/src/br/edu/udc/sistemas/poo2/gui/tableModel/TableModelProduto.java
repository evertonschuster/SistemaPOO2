package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Produto;

public class TableModelProduto extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelProduto() {
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
	Produto Produto = (Produto) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return Produto.getId();
	case 1:
	    return Produto.getDescricao();
	case 2:
	    return Produto.getValor();
	case 3:
	    return Produto.getQtd();
	case 4:
	    return Produto.getQtdMinimo();
	case 5:
	    return Produto.getMarca().getDescricao();
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
	case 3:
	    return "Quantidade";
	case 4:
	    return "Quantidade Minima";
	case 5:
	    return "Marca";
	}
	return "";
    }

}