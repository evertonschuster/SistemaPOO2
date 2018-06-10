package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelFornecedor;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFornecedor;

public class FormFindFornecedor extends FormFindContribuinte {

	private static final long serialVersionUID = 1L;

	protected JTextField tfNomeFantazia;
	protected JTextField tfRazaoSocial;
	protected JFormattedTextField tfCNPJ;

	private TableModelFornecedor tableModelFornecedor;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfNomeFantazia = new JTextField();
		this.tfRazaoSocial = new JTextField();

		try {
			this.tfCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
			this.tfCNPJ.setFocusLostBehavior(JFormattedTextField.PERSIST);
			this.tfDatNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDatNasc.setFocusLostBehavior(JFormattedTextField.PERSIST);
			this.tfDatNasc.setColumns(6);
			this.tfDatNasc.setValue(null);
			this.tfTelf = new JFormattedTextField(new MaskFormatter("(##) #####-####"));	
			this.tfTelf.setFocusLostBehavior(JFormattedTextField.PERSIST);
			this.tfCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			this.tfCelular.setFocusLostBehavior(JFormattedTextField.PERSIST);
			this.tfLogradouro = new JTextField();
			this.tfNumero = new JTextField();
			this.tfBairro = new JTextField();
			this.tfCidade = new JTextField();
			this.tfEstado = new JTextField();
			this.tfCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
			this.tfCEP.setFocusLostBehavior(JFormattedTextField.PERSIST);
			} catch (ParseException e) {
				e.printStackTrace();
		}		


		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdContribuinte);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Nome Fantazia:"));
		this.fieldsPanel.add(this.tfNomeFantazia);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Razao Social:"));
		this.fieldsPanel.add(this.tfRazaoSocial);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("CNPJ:"));
		this.fieldsPanel.add(this.tfCNPJ);
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
		this.tableModelFornecedor = new TableModelFornecedor();
		this.list.setModel(this.tableModelFornecedor);
		;
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		try {
			fornecedor.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
			fornecedor.setId(null);
		}

		if (this.tfNomeFantazia.getText().trim().isEmpty()) {
			fornecedor.setNomeFantazia(null);
		} else {
			fornecedor.setNomeFantazia(this.tfNomeFantazia.getText());
		}
		
		if (this.tfRazaoSocial.getText().trim().isEmpty()) {
			fornecedor.setRazaoSocial(null);
		} else {
			fornecedor.setRazaoSocial(this.tfRazaoSocial.getText());
		}
		
		if (this.tfCNPJ.getText().trim().isEmpty()) {
			fornecedor.setCNPJ(null);
		} else {
			fornecedor.setCNPJ(this.tfCNPJ.getText());
		}
		
		if (this.tfDatNasc.getText().contains("  /  /    ")) {
			fornecedor.setDataNascimento(null);
		} else {
			try {
				IOTools.validaData(this.tfDatNasc.getText());
				fornecedor.setDataNascimento(Date.valueOf(this.tfDatNasc.getText()));
			}catch (ExceptionValidacao e) {
				System.out.println(this.tfDatNasc.getText());
				throw e;
			}catch (Exception e) {
				fornecedor.setDataNascimento(null);
			}
		}
		
		if (this.tfTelf.getText().trim().isEmpty()) {
			fornecedor.setTelefone(null);
		} else {
			fornecedor.setTelefone(this.tfTelf.getText());
		}
		
		if (this.tfCelular.getText().trim().isEmpty()) {
			fornecedor.setCelular(null);
		} else {
			fornecedor.setCelular(this.tfCelular.getText());
		}
		
		if (this.tfLogradouro.getText().trim().isEmpty()) {
			fornecedor.setLogradouro(null);
		} else {
			fornecedor.setLogradouro(this.tfLogradouro.getText());
		}
		
		if (this.tfNumero.getText().trim().isEmpty()) {
			fornecedor.setNumero(null);
		} else {
			fornecedor.setNumero(this.tfNumero.getText());
		}
		
		if (this.tfBairro.getText().trim().isEmpty()) {
			fornecedor.setBairro(null);
		} else {
			fornecedor.setBairro(this.tfBairro.getText());
		}
		
		if (this.tfCidade.getText().trim().isEmpty()) {
			fornecedor.setCidade(null);
		} else {
			fornecedor.setCidade(this.tfCidade.getText());
		}
		
		if (this.tfEstado.getText().trim().isEmpty()) {
			fornecedor.setEstado(null);
		} else {
			fornecedor.setEstado(this.tfEstado.getText());
		}
		
		if (this.tfCEP.getText().trim().isEmpty()) {
			fornecedor.setCep(null);
		} else {
			fornecedor.setCep(this.tfCEP.getText());
		}


		SessionFornecedor sessionFornecedor = new SessionFornecedor();
		this.tableModelFornecedor.setList(sessionFornecedor.find(fornecedor));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfNomeFantazia.setText("");
		this.tfRazaoSocial.setText("");
		this.tfCNPJ.setText("");
		
		super.clean();
		
		this.tableModelFornecedor.setList(new Object[0]);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Fornecedor");
		this.getInternalFrame().setContentPane(new FormCreateFornecedor());
	}

	@Override
	protected void detail() throws Exception {
		Fornecedor fornecedor = (Fornecedor) this.tableModelFornecedor.getList()[this.list.getSelectedRow()];
		FormCreateFornecedor formManterFornecedor = new FormCreateFornecedor();
	    formManterFornecedor.setObject(fornecedor);
		getInternalFrame().setTitle("Manter Fornecedor");
		getInternalFrame().setContentPane(formManterFornecedor);
	}

}
