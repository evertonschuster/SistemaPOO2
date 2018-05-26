package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.rmi.CORBA.Tie;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.session.SessionMarca;
import br.edu.udc.sistemas.poo2.session.SessionProduto;

public class FormCreateProduto extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdProduto;
	private JTextField tfDescricao;
	private JTextField tfValor;
	private JTextField tfQTD;
	private JTextField tfQTDminimo;
	private JComboBox<Object> cmbMarca;

	@Override
	protected void createFieldsPanel() {
		this.tfIdProduto = new JTextField();
		this.tfIdProduto.setEnabled(false);
		this.tfIdProduto.setEditable(false);
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
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
	}

	@Override
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Descricao Invalida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfDescricao.requestFocus();
			return false;
		}

		if (this.cmbMarca.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a Marca!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.cmbMarca.requestFocus();
			return false;
		}
		
		if(this.tfValor.getText().trim().isEmpty() || Double.parseDouble(this.tfValor.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Valor do produto Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfValor.requestFocus();
			return false;
		}
		
		if(this.tfQTD.getText().trim().isEmpty() || Integer.parseInt(this.tfQTD.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Quantidade de Produto Invalida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfQTD.requestFocus();
			return false;
		}
		
		if(this.tfQTDminimo.getText().trim().isEmpty() || Integer.parseInt(this.tfQTDminimo.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Quantidade minima de Produto Invalida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfQTDminimo.requestFocus();
			return false;
		}
		
		return true;
	}

	@Override
	protected void save() throws Exception {
		Produto produto = new Produto();

		try {
			produto.setId(Integer.parseInt(this.tfIdProduto.getText()));
		} catch (Exception e) {
		}

		produto.setDescricao(this.tfDescricao.getText());
		produto.setMarca((Marca) this.cmbMarca.getSelectedItem());
		
		produto.setValor( Double.valueOf(this.tfValor.getText()));
		produto.setQtd(Integer.valueOf(this.tfQTD.getText()));
		produto.setQtdMinimo(Integer.valueOf(this.tfQTDminimo.getText()));
		
		SessionProduto sessionProduto = new SessionProduto();
		sessionProduto.save(produto);
		this.tfIdProduto.setText(String.valueOf(produto.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Produto produto = new Produto();
		try {
			produto.setId(Integer.parseInt(this.tfIdProduto.getText()));
		} catch (Exception e) {
		}
		SessionProduto sessionProduto = new SessionProduto();
		sessionProduto.remove(produto);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdProduto.setText("");
		this.tfDescricao.setText("");
		this.cmbMarca.setSelectedIndex(0);
		this.tfValor.setText("");
		this.tfQTD.setText("");
		this.tfQTDminimo.setText("");
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Produto");
		this.getInternalFrame().setContentPane(new FormFindProduto());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Produto) {
			Produto produto = (Produto) object;
			this.tfIdProduto.setText(String.valueOf(produto.getId()));
			this.tfDescricao.setText(produto.getDescricao());
			this.cmbMarca.setSelectedItem(produto.getMarca());
			this.tfValor.setText(produto.getValor().toString());
			this.tfQTD.setText(produto.getQtd().toString());
			this.tfQTDminimo.setText(produto.getQtdMinimo().toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}