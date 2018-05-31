package br.edu.udc.sistemas.poo2.gui.tableModel;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;

public class TableModelFornecedor extends TableModelContribuinte {

	  private static final long serialVersionUID = 1L;

	    //private Object list[];

	    public TableModelFornecedor() {
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
		return super.getColumnCount() + 3;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
		Fornecedor fornecedor = (Fornecedor) list[rowIndex];
		switch (columnIndex) {
		case 0:
		    return fornecedor.getId();
		case 1:
		    return fornecedor.getNomeFantazia();
		case 2:
			return fornecedor.getRazaoSocial();
		case 3:
			return fornecedor.getCNPJ();
		}

		return super.getValueAt(rowIndex, columnIndex - 3); 
		
	    }

	    @Override
	    public String getColumnName(int column) {
		switch (column) {
		case 0:
		    return "Codigo";
		case 1:
		    return "NomeFantazia";
		case 2:
		    return "razaoSocial";
		case 3:
		    return "CNPJ";
		}
		
		return super.getColumnName(column -3);
	    }

	}