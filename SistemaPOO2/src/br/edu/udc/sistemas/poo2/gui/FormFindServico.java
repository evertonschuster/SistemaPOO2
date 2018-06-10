package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelServico;
import br.edu.udc.sistemas.poo2.session.SessionServico;

public class FormFindServico extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdServico;
	private JTextField tfDescricao;
	private JTextField tfValor;
	private TableModelServico tableModelServico;

	@Override
	protected void createFieldsPanel() {
		this.tfIdServico = new JTextField();
		this.tfDescricao = new JTextField();
		this.tfValor = new JTextField();

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdServico);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Descricao:"));
		this.fieldsPanel.add(this.tfDescricao);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Valor:"));
		this.fieldsPanel.add(this.tfValor);
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelServico = new TableModelServico();
		this.list.setModel(this.tableModelServico);
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Servico Servico = new Servico();
		try {
			Servico.setId(Integer.parseInt(this.tfIdServico.getText()));
		} catch (Exception e) {
			Servico.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			Servico.setDescricao(null);
		} else {
			Servico.setDescricao(this.tfDescricao.getText());
		}

		SessionServico sessionServico = new SessionServico();
		this.tableModelServico.setList(sessionServico.find(Servico));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdServico.setText("");
		this.tfDescricao.setText("");
		this.tfValor.setText("");
		this.tableModelServico.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Servico");
		this.getInternalFrame().setContentPane(new FormCreateServico());
	}

	@Override
	protected void detail() throws Exception {
		Servico Servico = (Servico) this.tableModelServico.getList()[this.list.getSelectedRow()];
		FormCreateServico formManterServico = new FormCreateServico();
		formManterServico.setObject(Servico);
		getInternalFrame().setTitle("Manter Servico");
		getInternalFrame().setContentPane(formManterServico);
	}

}
