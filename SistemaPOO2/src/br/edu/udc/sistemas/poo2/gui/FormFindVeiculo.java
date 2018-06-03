package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Modelo;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelVeiculo;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionModelo;
import br.edu.udc.sistemas.poo2.session.SessionVeiculo;

public class FormFindVeiculo extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdVeiculo;
	private JTextField tfAno;
	private JTextField tfPlaca;
	private JTextField tfChassis;
	private JTextField tfCor;
	private JComboBox<Object> cmbModelo;
	private JComboBox<Object> cmbCliente;
	private TableModelVeiculo tableModelVeiculo;

	@Override
	protected void createFieldsPanel() {
		this.tfIdVeiculo = new JTextField();
		
		try {
			this.tfAno = new JFormattedTextField(new MaskFormatter("####"));
			} catch (ParseException e) {
				e.printStackTrace();
		}
		
		try {
			this.tfPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
			} catch (ParseException e) {
				e.printStackTrace();
		}
		
		try {
			this.tfChassis = new JFormattedTextField(new MaskFormatter("#################"));
			} catch (ParseException e) {
				e.printStackTrace();
		}
		

		this.tfCor = new JTextField();
		this.cmbModelo = new JComboBox<Object>();
		this.cmbCliente = new JComboBox<Object>();

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
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelVeiculo = new TableModelVeiculo();
		this.list.setModel(this.tableModelVeiculo);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Veiculo Veiculo = new Veiculo();
		try {
			Veiculo.setId(Integer.parseInt(this.tfIdVeiculo.getText()));
		} catch (Exception e) {
			Veiculo.setId(null);
		}

		if (this.tfAno.getText().trim().isEmpty()) {
			Veiculo.setAno(null);
		} else {
			Veiculo.setAno(this.tfAno.getText());
		}
		
		if (this.tfPlaca.getText().trim().isEmpty()) {
			Veiculo.setPlaca(null);
		} else {
			Veiculo.setPlaca(this.tfPlaca.getText());
		}
		
		if (this.tfChassis.getText().trim().isEmpty()) {
			Veiculo.setChassis(null);
		} else {
			Veiculo.setChassis(this.tfChassis.getText());
		}
		
		if (this.tfCor.getText().trim().isEmpty()) {
			Veiculo.setCor(null);
		} else {
			Veiculo.setCor(this.tfCor.getText());
		}

		if (this.cmbModelo.getSelectedIndex() > 0) {
			Veiculo.setModelo((Modelo) this.cmbModelo.getSelectedItem());
		}
		
		if (this.cmbCliente.getSelectedIndex() > 0) {
			Veiculo.setCliente((Cliente) this.cmbCliente.getSelectedItem());
		}

		SessionVeiculo sessionVeiculo = new SessionVeiculo();
		this.tableModelVeiculo.setList(sessionVeiculo.find(Veiculo));
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
		this.tableModelVeiculo.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Veiculo");
		this.getInternalFrame().setContentPane(new FormCreateVeiculo());
	}

	@Override
	protected void detail() throws Exception {
		Veiculo Veiculo = (Veiculo) this.tableModelVeiculo.getList()[this.list.getSelectedRow()];
		FormCreateVeiculo formManterVeiculo = new FormCreateVeiculo();
		formManterVeiculo.setObject(Veiculo);
		getInternalFrame().setTitle("Manter Veiculo");
		getInternalFrame().setContentPane(formManterVeiculo);
	}

}
