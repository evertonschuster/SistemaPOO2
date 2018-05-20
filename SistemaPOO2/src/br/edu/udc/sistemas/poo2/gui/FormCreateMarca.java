package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.session.SessionMarca;

public class FormCreateMarca extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdMarca;
	private JTextField tfDescricao;

	@Override
	protected void createFieldsPanel() {
		this.tfIdMarca = new JTextField();
		this.tfIdMarca.setEnabled(false);
		this.tfIdMarca.setEditable(false);
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
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Descrição Inválida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfDescricao.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Marca marca = new Marca();

		try {
			marca.setId(Integer.parseInt(this.tfIdMarca.getText()));
		} catch (Exception e) {
		}

		marca.setDescricao(this.tfDescricao.getText());
		SessionMarca sessionMarca = new SessionMarca();
		sessionMarca.save(marca);
		this.tfIdMarca.setText(String.valueOf(marca.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Marca marca = new Marca();
		try {
			marca.setId(Integer.parseInt(this.tfIdMarca.getText()));
		} catch (Exception e) {
		}
		SessionMarca sessionMarca = new SessionMarca();
		sessionMarca.remove(marca);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdMarca.setText("");
		this.tfDescricao.setText("");
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Marca");
		this.getInternalFrame().setContentPane(new FormFindMarca());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Marca) {
			Marca marca = (Marca) object;
			this.tfIdMarca.setText(String.valueOf(marca.getId()));
			this.tfDescricao.setText(marca.getDescricao());
		}
	}
}
