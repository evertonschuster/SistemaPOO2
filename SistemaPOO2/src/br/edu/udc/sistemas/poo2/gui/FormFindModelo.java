package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelModelo;
import br.edu.udc.sistemas.poo2.session.SessionMarca;
import br.edu.udc.sistemas.poo2.session.SessionModelo;

public class FormFindModelo extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdModelo;
	private JTextField tfDescricao;
	private JComboBox<Object> cmbMarca;
	private TableModelModelo tableModelModelo;

	@Override
	protected void createFieldsPanel() {
		this.tfIdModelo = new JTextField();
		this.tfDescricao = new JTextField();
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
		this.fieldsPanel.add(new JLabel("Código:"));
		this.fieldsPanel.add(this.tfIdModelo);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descrição:"));
		this.fieldsPanel.add(this.tfDescricao);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Marca:"));
		this.fieldsPanel.add(this.cmbMarca);
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelModelo = new TableModelModelo();
		this.list.setModel(this.tableModelModelo);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Modelo modelo = new Modelo();
		try {
			modelo.setId(Integer.parseInt(this.tfIdModelo.getText()));
		} catch (Exception e) {
			modelo.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			modelo.setDescricao(null);
		} else {
			modelo.setDescricao(this.tfDescricao.getText());
		}

		if (this.cmbMarca.getSelectedIndex() > 0) {
			modelo.setMarca((Marca) this.cmbMarca.getSelectedItem());
		}

		SessionModelo sessionModelo = new SessionModelo();
		this.tableModelModelo.setList(sessionModelo.find(modelo));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdModelo.setText("");
		this.tfDescricao.setText("");
		this.cmbMarca.setSelectedIndex(0);
		this.tableModelModelo.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Modelo");
		this.getInternalFrame().setContentPane(new FormCreateModelo());
	}

	@Override
	protected void detail() throws Exception {
		Modelo modelo = (Modelo) this.tableModelModelo.getList()[this.list.getSelectedRow()];
		FormCreateModelo formManterModelo = new FormCreateModelo();
		formManterModelo.setObject(modelo);
		getInternalFrame().setTitle("Manter Modelo");
		getInternalFrame().setContentPane(formManterModelo);
	}

}
