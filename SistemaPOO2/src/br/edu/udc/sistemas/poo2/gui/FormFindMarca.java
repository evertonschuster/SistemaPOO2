package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelMarca;
import br.edu.udc.sistemas.poo2.session.SessionMarca;

public class FormFindMarca extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdMarca;
	private JTextField tfDescricao;
	private TableModelMarca tableModelMarca;

	@Override
	protected void createFieldsPanel() {
		this.tfIdMarca = new JTextField();
		this.tfDescricao = new JTextField();

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Código:"));
		this.fieldsPanel.add(this.tfIdMarca);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descrição:"));
		this.fieldsPanel.add(this.tfDescricao);
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelMarca = new TableModelMarca();
		this.list.setModel(this.tableModelMarca);
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Marca marca = new Marca();
		try {
			marca.setId(Integer.parseInt(this.tfIdMarca.getText()));
		} catch (Exception e) {
			marca.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			marca.setDescricao(null);
		} else {
			marca.setDescricao(this.tfDescricao.getText());
		}

		SessionMarca sessionMarca = new SessionMarca();
		this.tableModelMarca.setList(sessionMarca.find(marca));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdMarca.setText("");
		this.tfDescricao.setText("");
		this.tableModelMarca.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Marca");
		this.getInternalFrame().setContentPane(new FormCreateMarca());
	}

	@Override
	protected void detail() throws Exception {
		Marca marca = (Marca) this.tableModelMarca.getList()[this.list.getSelectedRow()];
		FormCreateMarca formManterMarca = new FormCreateMarca();
		formManterMarca.setObject(marca);
		getInternalFrame().setTitle("Manter Marca");
		getInternalFrame().setContentPane(formManterMarca);
	}

}
