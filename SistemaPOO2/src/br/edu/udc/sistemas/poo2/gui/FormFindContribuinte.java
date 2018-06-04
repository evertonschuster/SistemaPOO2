package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Contribuinte;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelContribuinte;
import br.edu.udc.sistemas.poo2.session.SessionContribuinte;

public class FormFindContribuinte extends FormFind {
	
	
	private static final long serialVersionUID = 1L;

	protected JTextField tfIdContribuinte;
	protected JFormattedTextField tfDatNasc;
	protected JTextField tfTelf;
	protected JTextField tfCelular;
	protected JTextField tfLogradouro;
	protected JTextField tfNumero;
	protected JTextField tfBairro;
	protected JTextField tfCidade;
	protected JTextField tfEstado;
	protected JTextField tfCEP;
	protected TableModelContribuinte tableModelContribuinte;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		try {
			this.tfDatNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDatNasc.setColumns(6);
			this.tfDatNasc.setValue(null);		
			this.tfTelf = new JFormattedTextField(new MaskFormatter("(##) #####-####"));	
			this.tfCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));			
			this.tfLogradouro = new JTextField();
			this.tfNumero = new JTextField();
			this.tfBairro = new JTextField();
			this.tfCidade = new JTextField();
			this.tfEstado = new JTextField();
			this.tfCEP = new JFormattedTextField(new MaskFormatter("#####-###"));	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		


		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdContribuinte);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Data de Nascimento:"));
		this.fieldsPanel.add(this.tfDatNasc);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Telefone:"));
		this.fieldsPanel.add(this.tfTelf);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Celular:"));
		this.fieldsPanel.add(this.tfCelular);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Logradouro:"));
		this.fieldsPanel.add(this.tfLogradouro);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Numero:"));
		this.fieldsPanel.add(this.tfNumero);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Bairro:"));
		this.fieldsPanel.add(this.tfBairro);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Cidade:"));
		this.fieldsPanel.add(this.tfCidade);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Estado:"));
		this.fieldsPanel.add(this.tfEstado);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("CEP:"));
		this.fieldsPanel.add(this.tfCEP);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelContribuinte = new TableModelContribuinte();
		this.list.setModel(this.tableModelContribuinte);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Contribuinte contribuinte = new Contribuinte();
		try {
			contribuinte.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
			contribuinte.setId(null);
		}
		
		if (this.tfDatNasc.getText().trim().isEmpty()) {
			contribuinte.setDataNascimento(null);
		} else {
			contribuinte.setDataNascimento(Date.valueOf(this.tfDatNasc.getText()));
		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			contribuinte.setTelefone(null);
		} else {
			contribuinte.setTelefone(this.tfTelf.getText());
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			contribuinte.setCelular(null);
		} else {
			contribuinte.setCelular(this.tfCelular.getText());
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			contribuinte.setLogradouro(null);
		} else {
			contribuinte.setLogradouro(this.tfLogradouro.getText());
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			contribuinte.setNumero(null);
		} else {
			contribuinte.setNumero(this.tfNumero.getText());
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			contribuinte.setBairro(null);
		} else {
			contribuinte.setBairro(this.tfBairro.getText());
		}
		
		if (this.tfCidade.getText().trim().isEmpty()) {
			contribuinte.setCidade(null);
		} else {
			contribuinte.setCidade(this.tfCidade.getText());
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			contribuinte.setEstado(null);
		} else {
			contribuinte.setEstado(this.tfEstado.getText());
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			contribuinte.setCep(null);
		} else {
			contribuinte.setCep(this.tfCEP.getText());
		}


		SessionContribuinte sessionContribuinte = new SessionContribuinte();
		this.tableModelContribuinte.setList(sessionContribuinte.find(contribuinte));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfDatNasc.setText("");
		this.tfTelf.setText("");
		this.tfCelular.setText("");
		this.tfLogradouro .setText("");
		this.tfNumero.setText("");
		this.tfBairro.setText("");
		this.tfCidade.setText("");
		this.tfEstado.setText("");
		this.tfCEP.setText("");
		
		this.tableModelContribuinte.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Contribuinte");
		this.getInternalFrame().setContentPane(new FormCreateContribuinte());
	}

	@Override
	protected void detail() throws Exception {
		Contribuinte contribuinte = (Contribuinte) this.tableModelContribuinte.getList()[this.list.getSelectedRow()];
		FormCreateContribuinte formManterContribuinte = new FormCreateContribuinte();
	    formManterContribuinte.setObject(contribuinte);
		getInternalFrame().setTitle("Manter Contribuinte");
		getInternalFrame().setContentPane(formManterContribuinte);
	}

}
