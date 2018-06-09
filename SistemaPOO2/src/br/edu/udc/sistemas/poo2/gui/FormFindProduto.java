package br.edu.udc.sistemas.poo2.gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.rmi.CORBA.Tie;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelProduto;
import br.edu.udc.sistemas.poo2.session.SessionMarca;
import br.edu.udc.sistemas.poo2.session.SessionProduto;

public class FormFindProduto extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdProduto;
	private JTextField tfDescricao;
	private JTextField tfValor;
	private JTextField tfQTD;
	private JTextField tfQTDminimo;
	private JComboBox<Object> cmbMarca;
	private TableModelProduto tableModelProduto;

	@Override
	protected void createFieldsPanel() {
		this.tfIdProduto = new JTextField();
		this.tfDescricao = new JTextField();
		this.tfValor = new JTextField();
		this.tfQTD = new JTextField();
		this.tfQTDminimo = new JTextField();
		this.cmbMarca = new JComboBox<Object>();

		Object listMarca[] = new Object[0];
		SessionMarca sessionMarca = new SessionMarca();
		try {
			listMarca = sessionMarca.find(new Marca());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.cmbMarca.addItem("Selecione");
		for (int i = 0; i < listMarca.length; i++) {
			this.cmbMarca.addItem(listMarca[i]);
		}

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdProduto);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descricao:"));
		this.fieldsPanel.add(this.tfDescricao);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Marca:"));
		this.fieldsPanel.add(this.cmbMarca);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Valor:"));
		this.fieldsPanel.add(this.tfValor);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Quantidade:"));
		this.fieldsPanel.add(this.tfQTD);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Quantidade Minima:"));
		this.fieldsPanel.add(this.tfQTDminimo);
		
		this.cmbMarca.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelProduto = new TableModelProduto();
		this.list.setModel(this.tableModelProduto);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Produto produto = new Produto();
		try {
			produto.setId(Integer.parseInt(this.tfIdProduto.getText()));
		} catch (Exception e) {
			produto.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			produto.setDescricao(null);
		} else {
			produto.setDescricao(this.tfDescricao.getText());
		}

		if (this.cmbMarca.getSelectedIndex() > 0) {
			produto.setMarca((Marca) this.cmbMarca.getSelectedItem());
		}
		
		if (this.tfValor.getText().trim().isEmpty()) {
			produto.setValor(null);
		} else {
			produto.setValor( Double.parseDouble(this.tfValor.getText()) );
		}
		
		if (this.tfQTD.getText().trim().isEmpty()) {
			produto.setQtd(null);
		} else {
			produto.setQtd(Integer.parseInt( this.tfQTD.getText()) );
		}
		
		if (this.tfQTDminimo.getText().trim().isEmpty()) {
			produto.setQtdMinimo(null);
		} else {
			produto.setQtdMinimo(Integer.parseInt( this.tfQTDminimo.getText()) );
		}

		SessionProduto sessionProduto = new SessionProduto();
		this.tableModelProduto.setList(sessionProduto.find(produto));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdProduto.setText("");
		this.tfDescricao.setText("");
		this.cmbMarca.setSelectedIndex(0);
		this.tableModelProduto.setList(new Object[0]);
		this.tfValor.setText("");
		this.tfQTDminimo.setText("");
		this.tfQTDminimo.setText("");
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Produto");
		this.getInternalFrame().setContentPane(new FormCreateProduto());
	}

	@Override
	protected void detail() throws Exception {
		Produto produto = (Produto) this.tableModelProduto.getList()[this.list.getSelectedRow()];
		FormCreateProduto formManterProduto = new FormCreateProduto();
		formManterProduto.setObject(produto);
		getInternalFrame().setTitle("Manter Produto");
		getInternalFrame().setContentPane(formManterProduto);
	}

}
