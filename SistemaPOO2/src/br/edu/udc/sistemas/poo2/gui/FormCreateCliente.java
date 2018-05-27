package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormCreateCliente extends FormCreateContribuinte {

	private static final long serialVersionUID = 1L;

	private JTextField tfNome;
	private JTextField tfRG;
	private JTextField tfCPF;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfIdContribuinte.setEnabled(false);
		this.tfIdContribuinte.setEditable(false);
		this.tfNome = new JTextField();
		this.tfRG = new JTextField();
		this.tfCPF = new JTextField();
		this.tfDtNasc = new JTextField();
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
		
	}

	@Override
	protected boolean validateFields() {
		if (this.tfNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfNome.requestFocus();
			return false;
		}
		
		
		return super.validateFields();
	}

	@Override
	protected void save() throws Exception {
		Cliente Cliente = new Cliente();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			Cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}

		Cliente.setNome(this.tfNome.getText());
		Cliente.setRG(this.tfRG.getText());
		Cliente.setCPF(this.tfCPF.getText());
		Cliente.setDataNascimento( sdf.parse(this.tfDtNasc.getText()) );
		Cliente.setTelefone(this.tfTelf.getText());
		Cliente.setCelular(this.tfCelular.getText());
		Cliente.setLogradouro(this.tfLogradouro.getText());
		Cliente.setNumero(this.tfNumero.getText());
		Cliente.setBairro(this.tfBairro.getText());
		Cliente.setCidade(this.tfCidade.getText());
		Cliente.setEstado(this.tfEstado.getText());
		Cliente.setCep(this.tfCEP.getText());
		
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.save(Cliente);
		this.tfIdContribuinte.setText(String.valueOf(Cliente.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Cliente Cliente = new Cliente();
		try {
			Cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}
		SessionCliente sessionCliente = new SessionCliente();
		sessionCliente.remove(Cliente);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfNome.setText("");
		this.tfRG.setText("");
		this.tfCPF.setText("");
/*		this.tfDtNasc.setText("");
		this.tfTelf.setText("");
		this.tfCelular.setText("");
		this.tfLogradouro.setText("");
		this.tfNumero.setText("");
		this.tfBairro.setText("");
		this.tfCidade.setText("");
		this.tfEstado.setText("");
		this.tfCEP.setText("");*/
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
			Cliente Cliente = (Cliente) object;
			this.tfIdContribuinte.setText(String.valueOf(Cliente.getId()));
			this.tfNome.setText(Cliente.getNome());
			this.tfRG.setText(Cliente.getRG());
			this.tfCPF.setText(Cliente.getCPF());
			this.tfDtNasc.setText(Cliente.getDataNascimentoString());
			this.tfTelf.setText(Cliente.getTelefone());
			this.tfCelular.setText(Cliente.getCelular());
			this.tfLogradouro.setText(Cliente.getLogradouro());
			this.tfNumero.setText(Cliente.getNumero());
			this.tfBairro.setText(Cliente.getBairro());
			this.tfCidade.setText(Cliente.getCidade());
			this.tfEstado.setText(Cliente.getEstado());
			this.tfCEP.setText(Cliente.getCep());
		}
	}
}