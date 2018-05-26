package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.session.SessionServico;

public class FormCreateServico extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdServico;
	private JTextField tfDescricao;
	private JTextField tfValor;

	@Override
	protected void createFieldsPanel() {
		this.tfIdServico = new JTextField();
		this.tfIdServico.setEnabled(false);
		this.tfIdServico.setEditable(false);
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
		return true;
	}

	@Override
	protected void save() throws Exception {
		Servico Servico = new Servico();

		try {
			Servico.setId(Integer.parseInt(this.tfIdServico.getText()));
		} catch (Exception e) {
		}

		Servico.setDescricao(this.tfDescricao.getText());
		SessionServico sessionServico = new SessionServico();
		sessionServico.save(Servico);
		this.tfIdServico.setText(String.valueOf(Servico.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Servico Servico = new Servico();
		try {
			Servico.setId(Integer.parseInt(this.tfIdServico.getText()));
		} catch (Exception e) {
		}
		SessionServico sessionServico = new SessionServico();
		sessionServico.remove(Servico);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdServico.setText("");
		this.tfDescricao.setText("");
		this.tfValor.setText("");
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Servico");
		this.getInternalFrame().setContentPane(new FormFindServico());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Servico) {
			Servico Servico = (Servico) object;
			this.tfIdServico.setText(String.valueOf(Servico.getId()));
			this.tfDescricao.setText(Servico.getDescricao());
			this.tfValor.setText(String.valueOf(Servico.getValor()));
		}
	}
}
