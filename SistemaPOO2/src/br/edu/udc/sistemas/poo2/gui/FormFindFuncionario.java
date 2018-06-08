package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelFuncionario;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;

public class FormFindFuncionario extends FormFindCliente {
	private static final long serialVersionUID = 1L;

	protected JTextField tfLogin;
	protected JTextField tfSenha;


	private TableModelFuncionario tableModelFuncionario;

	@Override
	protected void createFieldsPanel() {
		
		super.createFieldsPanel();

		this.tfLogin = new JTextField();
		this.tfSenha = new JTextField();
		
		try {
			this.tfRG = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
			this.tfCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			
			this.tfDatNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDatNasc.setColumns(6);
			this.tfDatNasc.setValue(null);
			this.tfTelf = new JFormattedTextField(new MaskFormatter("(##) #####-####"));	
			this.tfCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			this.tfCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		} catch (ParseException e) {
				e.printStackTrace();
		}

		this.fieldsPanel.add(new JLabel("Login:"));
		this.fieldsPanel.add(this.tfLogin);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Senha:"));
		this.fieldsPanel.add(this.tfSenha);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelFuncionario = new TableModelFuncionario();
		this.list.setModel(this.tableModelFuncionario);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Funcionario funcionario = new Funcionario();
		try {
			funcionario.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
			funcionario.setId(null);
		}

		if (this.tfNome.getText().trim().isEmpty()) {
			funcionario.setNome(null);
		} else {
			funcionario.setNome(this.tfNome.getText());
		}
		
		if (this.tfRG.getText().trim().isEmpty()) {
			funcionario.setRG(null);
		} else {
			funcionario.setRG(this.tfRG.getText());
		}
		
		if (this.tfCPF.getText().trim().isEmpty()) {
			funcionario.setCPF(null);
		} else {
			funcionario.setCPF(this.tfCPF.getText());
		}
		
		if (this.tfDatNasc.getText().contains("  /  /    ")) {
			funcionario.setDataNascimento(null);
		} else {
			
			try {
				IOTools.validaData(this.tfDatNasc.getText());
				funcionario.setDataNascimento(Date.valueOf(this.tfDatNasc.getText()));
			}catch (ExceptionValidacao e) {
				throw e;
			}catch (Exception e) {
				funcionario.setDataNascimento(null);
			}

		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			funcionario.setTelefone(null);
		} else {
			funcionario.setTelefone(this.tfTelf.getText());
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			funcionario.setCelular(null);
		} else {
			funcionario.setCelular(this.tfCelular.getText());
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			funcionario.setLogradouro(null);
		} else {
			funcionario.setLogradouro(this.tfLogradouro.getText());
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			funcionario.setNumero(null);
		} else {
			funcionario.setNumero(this.tfNumero.getText());
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			funcionario.setBairro(null);
		} else {
			funcionario.setBairro(this.tfBairro.getText());
		}
		
		if (this.tfCidade.getText().trim().isEmpty()) {
			funcionario.setCidade(null);
		} else {
			funcionario.setCidade(this.tfCidade.getText());
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			funcionario.setEstado(null);
		} else {
			funcionario.setEstado(this.tfEstado.getText());
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			funcionario.setCep(null);
		} else {
			funcionario.setCep(this.tfCEP.getText());
		}

		if (this.tfLogin.getText().trim().isEmpty()) {
			funcionario.setLogin(null);
		} else {
			funcionario.setLogin(this.tfLogin.getText());
		}
		
		if (this.tfSenha.getText().trim().isEmpty()) {
			funcionario.setSenha(null);
		} else {
			funcionario.setSenha(this.tfSenha.getText());
		}

		SessionFuncionario sessionFuncionario = new SessionFuncionario();
		this.tableModelFuncionario.setList(sessionFuncionario.find(funcionario));
	}

	@Override
	protected void clean() throws Exception {

		this.tfLogin.setText("");
		this.tfSenha.setText("");
		
		super.clean();
		
		this.tableModelFuncionario.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Funcionario");
		this.getInternalFrame().setContentPane(new FormCreateFuncionario());
	}

	@Override
	protected void detail() throws Exception {
		Funcionario funcionario = (Funcionario) this.tableModelFuncionario.getList()[this.list.getSelectedRow()];
		FormCreateFuncionario formManterFuncionario = new FormCreateFuncionario();
	    formManterFuncionario.setObject(funcionario);
		getInternalFrame().setTitle("Manter Funcionario");
		getInternalFrame().setContentPane(formManterFuncionario);
	}

}

