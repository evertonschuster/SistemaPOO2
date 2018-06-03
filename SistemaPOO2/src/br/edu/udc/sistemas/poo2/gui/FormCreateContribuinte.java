package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Contribuinte;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionContribuinte;

public class FormCreateContribuinte extends FormCreate {

	private static final long serialVersionUID = 1L;

	protected JTextField tfIdContribuinte;
	protected JFormattedTextField  tfDtNasc;
	protected JTextField tfTelf;
	protected JTextField tfCelular;
	protected JTextField tfLogradouro;
	protected JTextField tfNumero;
	protected JTextField tfBairro;
	protected JTextField tfCidade;
	protected JTextField tfEstado;
	protected JTextField tfCEP;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfIdContribuinte.setEnabled(false);
		this.tfIdContribuinte.setEditable(false);
		try {
			this.tfDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDtNasc.setColumns(6);
			this.tfDtNasc.setValue(null);
			
			this.tfTelf = new JFormattedTextField(new MaskFormatter("##.###.###-#"));	
			this.tfCelular = new JFormattedTextField(new MaskFormatter("##.###.###-#"));	
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
		this.fieldsPanel.add(this.tfDtNasc);
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
		
	}

	@Override
	protected boolean validateFields() {
		
		try {
			IOTools.validaData(this.tfDtNasc.getText());
		} catch (ExceptionValidacao e) {
			JOptionPane.showMessageDialog(this,e.getMessage().toString(), "Aviso!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (this.tfDtNasc.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Data de Nascimento Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfDtNasc.requestFocus();
			return false;
		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Telefone Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfTelf.requestFocus();
			return false;
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Celular Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCelular.requestFocus();
			return false;
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Logradouro Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfLogradouro.requestFocus();
			return false;
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Numero Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfNumero.requestFocus();
			return false;
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Bairro Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfBairro.requestFocus();
			return false;
		}

		if (this.tfCidade.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Cidade Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCidade.requestFocus();
			return false;
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Estado Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfEstado.requestFocus();
			return false;
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "CEP Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCEP.requestFocus();
			return false;
		}
		
		return true;
	}

	@Override
	protected void save() throws Exception {
		Contribuinte contribuinte = new Contribuinte();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			contribuinte.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}


		contribuinte.setDataNascimento( sdf.parse(this.tfDtNasc.getText()) );
		contribuinte.setTelefone(this.tfTelf.getText());
		contribuinte.setCelular(this.tfCelular.getText());
		contribuinte.setLogradouro(this.tfLogradouro.getText());
		contribuinte.setNumero(this.tfNumero.getText());
		contribuinte.setBairro(this.tfBairro.getText());
		contribuinte.setCidade(this.tfCidade.getText());
		contribuinte.setEstado(this.tfEstado.getText());
		contribuinte.setCep(this.tfCEP.getText());
		
		SessionContribuinte sessionContribuinte = new SessionContribuinte();
		sessionContribuinte.save(contribuinte);
		this.tfIdContribuinte.setText(String.valueOf(contribuinte.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Contribuinte contribuinte = new Contribuinte();
		try {
			contribuinte.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}
		SessionContribuinte sessionContribuinte = new SessionContribuinte();
		sessionContribuinte.remove(contribuinte);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfDtNasc.setText("");
		this.tfTelf.setText("");
		this.tfCelular.setText("");
		this.tfLogradouro.setText("");
		this.tfNumero.setText("");
		this.tfBairro.setText("");
		this.tfCidade.setText("");
		this.tfEstado.setText("");
		this.tfCEP.setText("");
		
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Contribuinte");
		this.getInternalFrame().setContentPane(new FormFindContribuinte());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Contribuinte) {
			Contribuinte contribuinte = (Contribuinte) object;
			this.tfIdContribuinte.setText(String.valueOf(contribuinte.getId()));
			this.tfDtNasc.setText(contribuinte.getDataNascimentoString());
			this.tfTelf.setText(contribuinte.getTelefone());
			this.tfCelular.setText(contribuinte.getCelular());
			this.tfLogradouro.setText(contribuinte.getLogradouro());
			this.tfNumero.setText(contribuinte.getNumero());
			this.tfBairro.setText(contribuinte.getBairro());
			this.tfCidade.setText(contribuinte.getCidade());
			this.tfEstado.setText(contribuinte.getEstado());
			this.tfCEP.setText(contribuinte.getCep());
		}
	}
}
