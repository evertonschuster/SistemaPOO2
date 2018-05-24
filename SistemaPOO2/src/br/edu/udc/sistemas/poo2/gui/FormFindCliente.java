package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelCliente;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormFindCliente extends FormFind {

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
	private TableModelCliente tableModelCliente;

	@Override
	protected void createFieldsPanel() {
		this.tfIdCliente = new JTextField();
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
		this.tableModelCliente = new TableModelCliente();
		this.list.setModel(this.tableModelCliente);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Cliente Cliente = new Cliente();
		try {
			Cliente.setId(Integer.parseInt(this.tfIdCliente.getText()));
		} catch (Exception e) {
			Cliente.setId(null);
		}

		if (this.tfNome.getText().trim().isEmpty()) {
			Cliente.setNome(null);
		} else {
			Cliente.setNome(this.tfNome.getText());
		}
		
		if (this.tfRG.getText().trim().isEmpty()) {
			Cliente.setRG(null);
		} else {
			Cliente.setRG(this.tfRG.getText());
		}
		
		if (this.tfCPF.getText().trim().isEmpty()) {
			Cliente.setCPF(null);
		} else {
			Cliente.setCPF(this.tfCPF.getText());
		}
		
		if (this.tfDatNasc.getText().trim().isEmpty()) {
			Cliente.setDtNasc(null);
		} else {
			Cliente.setDtNasc(this.tfDatNasc.getText());
		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			Cliente.setTelf(null);
		} else {
			Cliente.setTelf(this.tfTelf.getText());
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			Cliente.setCelular(null);
		} else {
			Cliente.setCelular(this.tfCelular.getText());
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			Cliente.setLogradoudo(null);
		} else {
			Cliente.setLogradoudo(this.tfLogradouro.getText());
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			Cliente.setNumero(null);
		} else {
			Cliente.setNumero(this.tfNumero.getText());
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			Cliente.setBairro(null);
		} else {
			Cliente.setBairro(this.tfBairro.getText());
		}
		
		if (this.tfCidade.getText().trim().isEmpty()) {
			Cliente.setCidade(null);
		} else {
			Cliente.setCidade(this.tfCidade.getText());
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			Cliente.setEstado(null);
		} else {
			Cliente.setEstado(this.tfEstado.getText());
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			Cliente.setCep(null);
		} else {
			Cliente.setCep(this.tfCEP.getText());
		}


		SessionCliente sessionCliente = new SessionCliente();
		this.tableModelCliente.setList(sessionCliente.find(Cliente));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdCliente.setText("");
		this.tfNome.setText("");
		this.tfRG.setText("");
		this.tfCPF.setText("");
		this.tfDatNasc.setText("");
		this.tfTelf.setText("");
		this.tfCelular.setText("");
		this.tfLogradouro .setText("");
		this.tfNumero.setText("");
		this.tfBairro.setText("");
		this.tfCidade.setText("");
		this.tfEstado.setText("");
		this.tfCEP.setText("");
		
		this.tableModelCliente.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Cliente");
		this.getInternalFrame().setContentPane(new FormCreateCliente());
	}

	@Override
	protected void detail() throws Exception {
		Cliente Cliente = (Cliente) this.tableModelCliente.getList()[this.list.getSelectedRow()];
		FormCreateCliente formManterCliente = new FormCreateCliente();
	    formManterCliente.setObject(Cliente);
		getInternalFrame().setTitle("Manter Cliente");
		getInternalFrame().setContentPane(formManterCliente);
	}

}
