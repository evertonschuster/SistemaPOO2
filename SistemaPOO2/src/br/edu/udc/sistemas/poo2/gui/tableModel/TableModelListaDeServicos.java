package br.edu.udc.sistemas.poo2.gui.tableModel;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;

public class TableModelListaDeServicos  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelListaDeServicos() {
	this.list = new Object[0];
    }

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
        this.list = list;
        this.fireTableDataChanged();
    }
    
    public void addProduto(Object list) {
    	ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(this.list));
    	temp.add(list);
    	this.list = temp.toArray();
        this.fireTableDataChanged();
    }
    
    public void removeProduto(Object list) {
    	ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(this.list));
    	temp.remove(list);
    	this.list = temp.toArray();
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
	ListaDeProduto listaDeProduto = (ListaDeProduto) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return listaDeProduto.getProduto().getId();
	case 1:
	    return listaDeProduto.getProduto().getDescricao();
	case 2:
	    return listaDeProduto.getProduto().getValor();
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
