package br.edu.udc.sistemas.poo2.gui.tableModel;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.ListaDeServico;

public class TableModelListaDeProdutoServico extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelListaDeProdutoServico() {
	this.list = new Object[0];
    }

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
        this.list = list;
        this.fireTableDataChanged();
    }
    
    public boolean addProduto(Object list) {
    	ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(this.list));
    	
    	//se for produto e ja estiver na grid, apenas add a quantidade de produtos,  para n repetir produtos
    	if(list instanceof ListaDeProduto) {
    		int i = temp.indexOf(list);     //lastIndexOf(list);
    		if(i >= 0) {
            	ListaDeProduto obj = (ListaDeProduto)temp.get(i);
            	obj.setQnt(obj.getQnt() + ((ListaDeProduto)list).getQnt());
            	this.fireTableDataChanged();
        		return true;
    		}
    	//verefica se servico ja esta na nota
    	}else if(list instanceof ListaDeServico) {
    		if(temp.contains(list)){
    			return false;
    		}
    	}else {
    		return false;
    	}
    	
    	
    	temp.add(list);
    	this.list = temp.toArray();
        this.fireTableDataChanged();
        return true;
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
	return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if(list[rowIndex] instanceof ListaDeProduto ) {
    		ListaDeProduto listaDeProduto = (ListaDeProduto) list[rowIndex];
    		switch (columnIndex) {
    		case 0:
    		    return listaDeProduto.getProduto().getId();
    		case 1:
    		    return listaDeProduto.getProduto().getDescricao();
    		case 2:
    		    return listaDeProduto.getProduto().getValor();
    		case 3:
    		    return listaDeProduto.getQnt();
    		case 4:
    		    return listaDeProduto.getQnt() * listaDeProduto.getProduto().getValor();
    		}
    		return "";
    	}else if(list[rowIndex] instanceof ListaDeServico){
    		ListaDeServico listaDeServico = (ListaDeServico) list[rowIndex];
    		switch (columnIndex) {
    		case 0:
    		    return listaDeServico.getServico().getId();
    		case 1:
    		    return listaDeServico.getServico().getDescricao();
    		case 2:
    		    return listaDeServico.getServico().getValor();
    		case 3:
    		    return "";
    		case 4:
    		    return listaDeServico.getServico().getValor();
    		}
    		return "";
    	}else {
    		return "";
    	}
		
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
		return "Total";
	    
	}
	return "";
    }

}
