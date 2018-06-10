package br.edu.udc.sistemas.poo2.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionModelo;
import br.edu.udc.sistemas.poo2.session.SessionVeiculo;

public class FormCreateVeiculo extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdVeiculo;
	private JFormattedTextField tfAno;
	private JFormattedTextField tfPlaca;
	private JTextField tfChassis;
	private JTextField tfCor;
	private JComboBox<Object> cmbModelo;
	private JComboBox<Object> cmbCliente;
	
	@Override
	protected void createFieldsPanel() {
		this.tfIdVeiculo = new JTextField();
		this.tfIdVeiculo.setEnabled(false);
		this.tfIdVeiculo.setEditable(false);
		try {
			this.tfAno = new JFormattedTextField(new MaskFormatter("####"));	
			this.tfAno.setFocusLostBehavior(JFormattedTextField.PERSIST);
			
			this.tfPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));	
			this.tfPlaca.setFocusLostBehavior(JFormattedTextField.PERSIST);
			
			this.tfChassis = new JTextField();
			this.tfCor = new JTextField();
			this.cmbModelo = new JComboBox<Object>();
			this.cmbCliente = new JComboBox<Object>();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Object listModelo[] = new Object[0];
		SessionModelo sessionModelo = new SessionModelo();
		try {
			listModelo = sessionModelo.find(new Modelo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.cmbModelo.addItem("Selecione");
		for (int i = 0; i < listModelo.length; i++) {
			this.cmbModelo.addItem(listModelo[i]);
		}
		this.cmbModelo.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		
		Object listCliente[] = new Object[0];
		SessionCliente sessionCliente = new SessionCliente();
		try {
			listCliente = sessionCliente.find(new Cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.cmbCliente.addItem("Selecione");
		for (int i = 0; i < listCliente.length; i++) {
			this.cmbCliente.addItem(listCliente[i]);
		}
		this.cmbCliente.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		
		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdVeiculo);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Ano:"));
		this.fieldsPanel.add(this.tfAno);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Placa:"));
		this.fieldsPanel.add(this.tfPlaca);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Cor:"));
		this.fieldsPanel.add(this.tfCor);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Chassis:"));
		this.fieldsPanel.add(this.tfChassis);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Modelo:"));
		this.fieldsPanel.add(this.cmbModelo);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Cliente:"));
		this.fieldsPanel.add(this.cmbCliente);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
	}

	@Override
	protected boolean validateFields() {
		if (this.tfAno.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ano Invalida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfAno.requestFocus();
			return false;
		}

		if (this.cmbModelo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a Modelo!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.cmbModelo.requestFocus();
			return false;
		}
		
		if (this.cmbCliente.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecione a Cliente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.cmbCliente.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Veiculo Veiculo = new Veiculo();

		try {
			Veiculo.setId(Integer.parseInt(this.tfIdVeiculo.getText()));
		} catch (Exception e) {
		}

		Veiculo.setAno(this.tfAno.getText());
		Veiculo.setPlaca(this.tfPlaca.getText());
		Veiculo.setChassis(this.tfChassis.getText());
		Veiculo.setCor(this.tfCor.getText());
		Veiculo.setModelo((Modelo) this.cmbModelo.getSelectedItem());
		Veiculo.setCliente((Cliente) this.cmbCliente.getSelectedItem());
		SessionVeiculo sessionVeiculo = new SessionVeiculo();
		sessionVeiculo.save(Veiculo);
		this.tfIdVeiculo.setText(String.valueOf(Veiculo.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Veiculo Veiculo = new Veiculo();
		try {
			Veiculo.setId(Integer.parseInt(this.tfIdVeiculo.getText()));
		} catch (Exception e) {
		}
		SessionVeiculo sessionVeiculo = new SessionVeiculo();
		sessionVeiculo.remove(Veiculo);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdVeiculo.setText("");
		this.tfAno.setText("");
		this.tfPlaca.setText("");
		this.tfChassis.setText("");
		this.tfCor.setText("");
		this.cmbModelo.setSelectedIndex(0);
		this.cmbCliente.setSelectedIndex(0);
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Veiculo");
		this.getInternalFrame().setContentPane(new FormFindVeiculo());
	}
	

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Veiculo) {
			Veiculo Veiculo = (Veiculo) object;
			this.tfIdVeiculo.setText(String.valueOf(Veiculo.getId()));
			this.tfAno.setText(String.valueOf(Veiculo.getAno().toString()));
			this.tfPlaca.setText(String.valueOf(Veiculo.getPlaca().toString()));
			this.tfChassis.setText(String.valueOf(Veiculo.getChassis()));
			this.tfCor.setText(Veiculo.getCor());
			this.cmbModelo.setSelectedItem(Veiculo.getModelo());
			this.cmbCliente.setSelectedItem(Veiculo.getCliente());
		}
	}
}