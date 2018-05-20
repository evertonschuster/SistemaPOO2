package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.session.SessionMarca;
import br.edu.udc.sistemas.poo2.session.SessionModelo;

public class FormCreateModelo extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdModelo;
	private JTextField tfDescricao;
	private JComboBox<Object> cmbMarca;

	@Override
	protected void createFieldsPanel() {
		this.tfIdModelo = new JTextField();
		this.tfIdModelo.setEnabled(false);
		this.tfIdModelo.setEditable(false);
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
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Descrição Inválida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfDescricao.requestFocus();
			return false;
		}

		if (this.cmbMarca.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a Marca!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.cmbMarca.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Modelo modelo = new Modelo();

		try {
			modelo.setId(Integer.parseInt(this.tfIdModelo.getText()));
		} catch (Exception e) {
		}

		modelo.setDescricao(this.tfDescricao.getText());
		modelo.setMarca((Marca) this.cmbMarca.getSelectedItem());
		SessionModelo sessionModelo = new SessionModelo();
		sessionModelo.save(modelo);
		this.tfIdModelo.setText(String.valueOf(modelo.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Modelo modelo = new Modelo();
		try {
			modelo.setId(Integer.parseInt(this.tfIdModelo.getText()));
		} catch (Exception e) {
		}
		SessionModelo sessionModelo = new SessionModelo();
		sessionModelo.remove(modelo);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdModelo.setText("");
		this.tfDescricao.setText("");
		this.cmbMarca.setSelectedIndex(0);
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Modelo");
		this.getInternalFrame().setContentPane(new FormFindModelo());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Modelo) {
			Modelo modelo = (Modelo) object;
			this.tfIdModelo.setText(String.valueOf(modelo.getId()));
			this.tfDescricao.setText(modelo.getDescricao());
			this.cmbMarca.setSelectedItem(modelo.getMarca());
		}
	}
}