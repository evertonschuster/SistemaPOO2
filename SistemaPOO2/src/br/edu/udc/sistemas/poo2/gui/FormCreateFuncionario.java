package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.Marca;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;
import br.edu.udc.sistemas.poo2.session.SessionMarca;

public class FormCreateFuncionario extends FormCreateCliente {

	private static final long serialVersionUID = 1L;

	private JTextField tfLogin;
	private JPasswordField  tfSenha;
	private JComboBox<Object> cmbCliente;
	
	class EventosPage implements ItemListener, FocusListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			try {
				setObject(cmbCliente.getSelectedItem());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		@Override
		public void focusGained(FocusEvent e) {

		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if(!tfIdContribuinte.getText().isEmpty()) {
				return;
			}
			
			if(tfCPF.getText().isEmpty()) {
				return;
			}
			SessionCliente sessionCliente = new SessionCliente();
			try {
				Cliente cliente = new Cliente();
				cliente.setCPF(tfCPF.getText());
				cliente = (Cliente)sessionCliente.find(cliente)[0];
				setObject(cliente);
				cmbCliente.setSelectedItem(cliente);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
		}
		
	}

	@Override
	protected void createFieldsPanel() {

		this.tfLogin = new JTextField();
		this.tfSenha = new JPasswordField();
		this.cmbCliente = new JComboBox<Object>();
		
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
		
		this.fieldsPanel.add(new JLabel("Cadastro apartir de Cliente:"));
		this.fieldsPanel.add(cmbCliente);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));	
		
		super.createFieldsPanel();
		
		this.fieldsPanel.add(new JLabel("Login:"));
		this.fieldsPanel.add(this.tfLogin);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Senha:"));
		this.fieldsPanel.add(this.tfSenha);
		
		cmbCliente.addItemListener(new EventosPage() );
		this.tfCPF.addFocusListener(new EventosPage() );
	}

	@Override
	protected boolean validateFields() {
		if (this.tfLogin.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Login Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfLogin.requestFocus();
			return false;
		}
		
		if (this.tfSenha.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Senha Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfSenha.requestFocus();
			return false;
		}

		
		try {
			IOTools.validaPassword( this.tfSenha.getText() );
		} catch (ExceptionValidacao e) {
			JOptionPane.showMessageDialog(this, "Senha Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfSenha.requestFocus();
			return false;
		}
		
		return super.validateFields();
	}

	@Override
	protected void save() throws Exception {
		Funcionario funcionario = new Funcionario();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			funcionario.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}

		funcionario.setNome(this.tfNome.getText());
		funcionario.setRG(this.tfRG.getText());
		funcionario.setCPF(this.tfCPF.getText());
		funcionario.setDataNascimento( sdf.parse(this.tfDtNasc.getText()) );
		funcionario.setTelefone(this.tfTelf.getText());
		funcionario.setCelular(this.tfCelular.getText());
		funcionario.setLogradouro(this.tfLogradouro.getText());
		funcionario.setNumero(this.tfNumero.getText());
		funcionario.setBairro(this.tfBairro.getText());
		funcionario.setCidade(this.tfCidade.getText());
		funcionario.setEstado(this.tfEstado.getText());
		funcionario.setCep(this.tfCEP.getText());
		
		funcionario.setLogin(this.tfLogin.getText());
		funcionario.setSenha(this.tfSenha.getText());
		
		SessionFuncionario sessionFuncionario = new SessionFuncionario();
		sessionFuncionario.save(funcionario);
		this.tfIdContribuinte.setText(String.valueOf(funcionario.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Funcionario funcionario = new Funcionario();
		try {
			funcionario.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}
		SessionFuncionario sessionFuncionario = new SessionFuncionario();
		sessionFuncionario.remove(funcionario);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfLogin.setText("");
		this.tfSenha.setText("");
	    this.cmbCliente.setSelectedIndex(0);
		
		super.clean();
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Funcionario");
		this.getInternalFrame().setContentPane(new FormFindFuncionario());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Funcionario) {
			Funcionario funcionario = (Funcionario) object;
			
			this.cmbCliente.setSelectedItem(funcionario);
			this.tfLogin.setText(funcionario.getLogin());
			this.tfSenha.setText(funcionario.getSenha());
			
			super.setObject(funcionario);
			
		}else {
			super.setObject(object);
			this.tfLogin.setText("");
			this.tfSenha.setText("");
		}
	}
}
