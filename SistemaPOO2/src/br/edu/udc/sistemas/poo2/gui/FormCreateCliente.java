package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormCreateCliente extends FormCreateContribuinte {

	private static final long serialVersionUID = 1L;

	protected JTextField tfNome;
	protected JTextField tfRG;
	protected JTextField tfCPF;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfIdContribuinte.setEnabled(false);
		this.tfIdContribuinte.setEditable(false);
		this.tfNome = new JTextField();
		try {
			this.tfRG = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
			this.tfCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));

			
			this.tfDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDtNasc.setColumns(6);
			this.tfDtNasc.setValue(null);
		
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
		this.fieldsPanel.add(new JLabel("Nome:"));
		this.fieldsPanel.add(this.tfNome);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("RG:"));
		this.fieldsPanel.add(this.tfRG);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("CPF:"));
		this.fieldsPanel.add(this.tfCPF);
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
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
	}

	@Override
	protected boolean validateFields() {
		if (this.tfNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfNome.requestFocus();
			return false;
		}
		
		if (this.tfRG.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "RG Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfRG.requestFocus();
			return false;
		}
		
		
		if (this.tfCPF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "CPF Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCPF.requestFocus();
			return false;
		}
		
		try {
			IOTools.validaCPF( this.tfCPF.getText() );
		} catch (ExceptionValidacao e) {
			JOptionPane.showMessageDialog(this, "CPF Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCPF.requestFocus();
			return false;
		}
		
		return super.validateFields();
	}

	@Override
	protected void save() throws Exception {
		Cliente cliente = new Cliente();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}

		cliente.setNome(this.tfNome.getText());
		cliente.setRG(this.tfRG.getText());
		cliente.setCPF(this.tfCPF.getText());
		cliente.setDataNascimento( sdf.parse(this.tfDtNasc.getText()) );
		cliente.setTelefone(this.tfTelf.getText());
		cliente.setCelular(this.tfCelular.getText());
		cliente.setLogradouro(this.tfLogradouro.getText());
		cliente.setNumero(this.tfNumero.getText());
		cliente.setBairro(this.tfBairro.getText());
		cliente.setCidade(this.tfCidade.getText());
		cliente.setEstado(this.tfEstado.getText());
		cliente.setCep(this.tfCEP.getText());
		
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.save(cliente);
		this.tfIdContribuinte.setText(String.valueOf(cliente.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Cliente cliente = new Cliente();
		try {
			cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.remove(cliente);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfNome.setText("");
		this.tfRG.setText("");
		this.tfCPF.setText("");		
		
		super.clean();
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Cliente");
		this.getInternalFrame().setContentPane(new FormFindCliente());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Cliente) {
			Cliente cliente = (Cliente) object;
			this.tfIdContribuinte.setText(String.valueOf(cliente.getId()));
			this.tfNome.setText(cliente.getNome());
			this.tfRG.setText(cliente.getRG());
			this.tfCPF.setText(cliente.getCPF());
			super.setObject(cliente);
//			this.tfDtNasc.setText(cliente.getDataNascimentoString());
//			this.tfTelf.setText(cliente.getTelefone());
//			this.tfCelular.setText(cliente.getCelular());
//			this.tfLogradouro.setText(cliente.getLogradouro());
//			this.tfNumero.setText(cliente.getNumero());
//			this.tfBairro.setText(cliente.getBairro());
//			this.tfCidade.setText(cliente.getCidade());
//			this.tfEstado.setText(cliente.getEstado());
//			this.tfCEP.setText(cliente.getCep());
		}
	}
}