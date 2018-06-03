package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelCliente;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionCliente;

public class FormFindCliente extends FormFindContribuinte {

	private static final long serialVersionUID = 1L;

	protected JTextField tfNome;
	protected JTextField tfRG;
	protected JTextField tfCPF;

	private TableModelCliente tableModelCliente;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfNome = new JTextField();
		
		try {
			this.tfRG = new JFormattedTextField(new MaskFormatter("##.###.###-#"));

			this.tfCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));

			this.tfDatNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDatNasc.setColumns(6);
			this.tfDatNasc.setValue(null);
		
			this.tfTelf = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
		
			this.tfCelular = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
			this.tfCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
			} catch (ParseException e) {
				e.printStackTrace();
		}
		

		this.tfLogradouro = new JTextField();
		this.tfNumero = new JTextField();
		this.tfBairro = new JTextField();
		this.tfCidade = new JTextField();
		this.tfEstado = new JTextField();

		


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
		Cliente cliente = new Cliente();
		try {
			cliente.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
			cliente.setId(null);
		}

		if (this.tfNome.getText().trim().isEmpty()) {
			cliente.setNome(null);
		} else {
			cliente.setNome(this.tfNome.getText());
		}
		
		if (this.tfRG.getText().trim().isEmpty()) {
			cliente.setRG(null);
		} else {
			cliente.setRG(this.tfRG.getText());
		}
		
		if (this.tfCPF.getText().trim().isEmpty()) {
			cliente.setCPF(null);
		} else {
			cliente.setCPF(this.tfCPF.getText());
		}
		
		if (this.tfDatNasc.getText().contains("  /  /    ")) {
			cliente.setDataNascimento(null);
		} else {
			try {
				IOTools.validaData(this.tfDatNasc.getText());
				cliente.setDataNascimento(Date.valueOf(this.tfDatNasc.getText()));
			}catch (ExceptionValidacao e) {
				System.out.println(this.tfDatNasc.getText());
				throw e;
			}catch (Exception e) {
				cliente.setDataNascimento(null);
			}
		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			cliente.setTelefone(null);
		} else {
			cliente.setTelefone(this.tfTelf.getText());
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			cliente.setCelular(null);
		} else {
			cliente.setCelular(this.tfCelular.getText());
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			cliente.setLogradouro(null);
		} else {
			cliente.setLogradouro(this.tfLogradouro.getText());
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			cliente.setNumero(null);
		} else {
			cliente.setNumero(this.tfNumero.getText());
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			cliente.setBairro(null);
		} else {
			cliente.setBairro(this.tfBairro.getText());
		}
		
		if (this.tfCidade.getText().trim().isEmpty()) {
			cliente.setCidade(null);
		} else {
			cliente.setCidade(this.tfCidade.getText());
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			cliente.setEstado(null);
		} else {
			cliente.setEstado(this.tfEstado.getText());
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			cliente.setCep(null);
		} else {
			cliente.setCep(this.tfCEP.getText());
		}


		SessionCliente sessionCliente = new SessionCliente();
		this.tableModelCliente.setList(sessionCliente.find(cliente));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfNome.setText("");
		this.tfRG.setText("");
		this.tfCPF.setText("");
		
		super.clean();
		
		this.tableModelCliente.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Cliente");
		this.getInternalFrame().setContentPane(new FormCreateCliente());
	}

	@Override
	protected void detail() throws Exception {
		Cliente cliente = (Cliente) this.tableModelCliente.getList()[this.list.getSelectedRow()];
		FormCreateCliente formManterCliente = new FormCreateCliente();
	    formManterCliente.setObject(cliente);
		getInternalFrame().setTitle("Manter Cliente");
		getInternalFrame().setContentPane(formManterCliente);
	}

}
