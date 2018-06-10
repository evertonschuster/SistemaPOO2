package br.edu.udc.sistemas.poo2.gui.tableModel;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2.entity.Veiculo;

public class TableModelVeiculo extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private Object list[];

    public TableModelVeiculo() {
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
	return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Veiculo veiculo = (Veiculo) list[rowIndex];
	switch (columnIndex) {
	case 0:
	    return veiculo.getId();
	case 1:
	    return veiculo.getAno();
	case 2:
	    return veiculo.getPlaca();
	case 3:
	    return veiculo.getChassis();
	case 4:
	    return veiculo.getCor();
	case 5:
	    return veiculo.getModelo().getDescricao();
	case 6:
		return veiculo.getCliente().getNome();
	}
	return "";
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	case 0:
	    return "Codigo";
	case 1:
	    return "Ano";
	case 2:
	    return "Placa";
	case 3:
	    return "Chassis";
	case 4:
	    return "Cor";
	case 5:
	    return "Modelo";
	case 6:
		return "Cliente";
	}
	return "";
    }

}