package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormCreateCliente extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdCliente;
	private JTextField tfNome;
	private JTextField tfRG;
	private JTextField tfCPF;
	private JTextField tfDatNasc;
	private JTextField tfTelf;
	private JTextField tfCelular;
	private JTextField tfLogradouro;
	private JTextField tfNumero;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JTextField tfEstado;
	private JTextField tfCEP;

	@Override
	protected void createFieldsPanel() {
		this.tfIdCliente = new JTextField();
		this.tfIdCliente.setEnabled(false);
		this.tfIdCliente.setEditable(false);
		this.tfNome = new JTextField();
		this.tfRG = new JTextField();
		this.tfCPF = new JTextField();
		this.tfDatNasc = new JTextField();
		this.tfTelf = new JTextField();
		this.tfCelular = new JTextField();
		this.tfLogradouro = new JTextField();
		this.tfNumero = new JTextField();
		this.tfBairro = new JTextField();
		this.tfCidade = new JTextField();
		this.tfEstado = new JTextField();
		this.tfCEP = new JTextField();


		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdCliente);
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
		this.fieldsPanel.add(new JLabel("Data Nascimento:"));
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
		
	}

	@Override
	protected boolean validateFields() {
		if (this.tfNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfNome.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Cliente Cliente = new Cliente();

		try {
			Cliente.setId(Integer.parseInt(this.tfIdCliente.getText()));
		} catch (Exception e) {
		}

		Cliente.setNome(this.tfNome.getText());
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.save(Cliente);
		this.tfIdCliente.setText(String.valueOf(Cliente.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Cliente Cliente = new Cliente();
		try {
			Cliente.setId(Integer.parseInt(this.tfIdCliente.getText()));
		} catch (Exception e) {
		}
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.remove(Cliente);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdCliente.setText("");
		this.tfNome.setText("");
		
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Cliente");
		this.getInternalFrame().setContentPane(new FormFindCliente());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Cliente) {
			Cliente Cliente = (Cliente) object;
			this.tfIdCliente.setText(String.valueOf(Cliente.getId()));
			this.tfNome.setText(Cliente.getNome());
		}
	}
}