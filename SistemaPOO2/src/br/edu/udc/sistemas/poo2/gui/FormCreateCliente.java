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
import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormCreateCliente extends FormCreateContribuinte {

	private static final long serialVersionUID = 1L;

	protected JTextField tfNome;
	protected JFormattedTextField tfRG;
	protected JFormattedTextField tfCPF;
	//protected JFormattedTextField tfDtNasc;
	//protected JFormattedTextField tfCelular;
	//protected JFormattedTextField tfTelf;
	//protected JFormattedTextField tfCEP;


	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfIdContribuinte.setEnabled(false);
		this.tfIdContribuinte.setEditable(false);
		this.tfNome = new JTextField();
		

		try {
			MaskFormatter mascara = new MaskFormatter("###.###.###-##");
			mascara.setValueContainsLiteralCharacters(false);
			this.tfRG = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
			this.tfRG.setFocusLostBehavior(JFormattedTextField.PERSIST);
			this.tfCPF = new JFormattedTextField(mascara);	
			this.tfCPF.setFocusLostBehavior(JFormattedTextField.PERSIST);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
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
		
		super.createFieldsPanel();
		
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
		
		return true;
	}

	@Override
	protected void save() throws Exception {
		Cliente cliente = new Cliente();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}

		cliente.setNome(this.tfNome.getText().replaceAll("[.-]","").trim());
		cliente.setRG(this.tfRG.getText().replaceAll("[.-]","").trim());
		cliente.setCPF(this.tfCPF.getText().replaceAll("[.-]","").trim());

		cliente.setDataNascimento( sdf.parse(this.tfDtNasc.getText().trim()) );
		cliente.setTelefone(this.tfTelf.getText().replaceAll("[.-]","").trim());
		cliente.setCelular(this.tfCelular.getText().replaceAll("[.-]","").trim());
		cliente.setLogradouro(this.tfLogradouro.getText().replaceAll("[.-]","").trim());
		cliente.setNumero(this.tfNumero.getText().replaceAll("[.-]","").trim());
		cliente.setBairro(this.tfBairro.getText().replaceAll("[.-]","").trim());
		cliente.setCidade(this.tfCidade.getText().replaceAll("[.-]","").trim());
		cliente.setEstado(this.tfEstado.getText().replaceAll("[.-]","").trim());
		cliente.setCep(this.tfCEP.getText().replaceAll("[.-]","").trim());
		
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
			
			
			System.out.println(cliente.getDataNascimentoString());
			
			
			this.tfIdContribuinte.setText(String.valueOf(cliente.getId()));
			this.tfNome.setText(cliente.getNome());
			this.tfRG.setText(cliente.getRG());
			this.tfCPF.setText(cliente.getCPF());
			super.setObject(cliente);
		}
	}
}