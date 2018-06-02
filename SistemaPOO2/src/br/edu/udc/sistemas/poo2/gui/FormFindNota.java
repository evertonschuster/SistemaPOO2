package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNota;
import br.edu.udc.sistemas.poo2.session.SessionNota;

public class FormFindNota extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdNota;
	private JTextField tfDescricao;
	private TableModelNota tableModelNota;

	@Override
	protected void createFieldsPanel() {
		this.tfIdNota = new JTextField();
		this.tfDescricao = new JTextField();

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("C�digo:"));
		this.fieldsPanel.add(this.tfIdNota);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descri��o:"));
		this.fieldsPanel.add(this.tfDescricao);
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelNota = new TableModelNota();
		this.list.setModel(this.tableModelNota);
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Nota nota = new Nota();
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
			nota.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			nota.setDescricao(null);
		} else {
			nota.setDescricao(this.tfDescricao.getText());
		}

		SessionNota sessionNota = new SessionNota();
		this.tableModelNota.setList(sessionNota.find(nota));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdNota.setText("");
		this.tfDescricao.setText("");
		this.tableModelNota.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Nota");
		this.getInternalFrame().setContentPane(new FormCreateNota());
	}

	@Override
	protected void detail() throws Exception {
		Nota nota = (Nota) this.tableModelNota.getList()[this.list.getSelectedRow()];
		FormCreateNota formManterNota = new FormCreateNota();
		formManterNota.setObject(nota);
		getInternalFrame().setTitle("Manter Nota");
		getInternalFrame().setContentPane(formManterNota);
	}

}
