package br.edu.udc.sistemas.poo2.gui;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFornecedor;

public class FormCreateFornecedor extends FormCreateContribuinte {
	private static final long serialVersionUID = 1L;

	protected JTextField tfNomeFantazia;
	protected JTextField tfrazaoSocial;
	protected JTextField tfCNPJ;

	@Override
	protected void createFieldsPanel() {
		this.tfIdContribuinte = new JTextField();
		this.tfIdContribuinte.setEnabled(false);
		this.tfIdContribuinte.setEditable(false);
		this.tfNomeFantazia = new JTextField();
		this.tfrazaoSocial = new JTextField();
		this.tfCNPJ = new JTextField();
		try {
			this.tfDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfDtNasc.setColumns(6);
			this.tfDtNasc.setValue(null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		this.fieldsPanel.add(new JLabel("Nome Fantazia:"));
		this.fieldsPanel.add(this.tfNomeFantazia);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Razao Social:"));
		this.fieldsPanel.add(this.tfrazaoSocial);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("CNPJ:"));
		this.fieldsPanel.add(this.tfCNPJ);
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
		if (this.tfNomeFantazia.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome Fantazia Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfNomeFantazia.requestFocus();
			return false;
		}
		
		if (this.tfrazaoSocial.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Razao Social Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfrazaoSocial.requestFocus();
			return false;
		}
		
		
		if (this.tfCNPJ.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "CNPJ Invalido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCNPJ.requestFocus();
			return false;
		}
		
		try {
			IOTools.validaCNPJ( this.tfCNPJ.getText() );
		} catch (ExceptionValidacao e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfCNPJ.requestFocus();
			return false;
		}
		
		return super.validateFields();
	}

	@Override
	protected void save() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			fornecedor.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}

		fornecedor.setNomeFantazia(this.tfNomeFantazia.getText());
		fornecedor.setRazaoSocial(this.tfrazaoSocial.getText());
		fornecedor.setCNPJ(this.tfCNPJ.getText());
		fornecedor.setDataNascimento( sdf.parse(this.tfDtNasc.getText()) );
		fornecedor.setTelefone(this.tfTelf.getText());
		fornecedor.setCelular(this.tfCelular.getText());
		fornecedor.setLogradouro(this.tfLogradouro.getText());
		fornecedor.setNumero(this.tfNumero.getText());
		fornecedor.setBairro(this.tfBairro.getText());
		fornecedor.setCidade(this.tfCidade.getText());
		fornecedor.setEstado(this.tfEstado.getText());
		fornecedor.setCep(this.tfCEP.getText());
		
		SessionFornecedor sessionFornecedor = new SessionFornecedor();
		sessionFornecedor.save(fornecedor);
		this.tfIdContribuinte.setText(String.valueOf(fornecedor.getId()));
	}

	@Override
	protected void remove() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		try {
			fornecedor.setId(Integer.parseInt(this.tfIdContribuinte.getText()));
		} catch (Exception e) {
		}
		SessionFornecedor sessionFornecedor = new SessionFornecedor();
		sessionFornecedor.remove(fornecedor);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdContribuinte.setText("");
		this.tfNomeFantazia.setText("");
		this.tfrazaoSocial.setText("");
		this.tfCNPJ.setText("");		
		
		super.clean();
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Fornecedor");
		this.getInternalFrame().setContentPane(new FormFindFornecedor());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Fornecedor) {
			Fornecedor fornecedor = (Fornecedor) object;
			this.tfIdContribuinte.setText(String.valueOf(fornecedor.getId()));
			this.tfNomeFantazia.setText(fornecedor.getNomeFantazia());
			this.tfrazaoSocial.setText(fornecedor.getRazaoSocial());
			this.tfCNPJ.setText(fornecedor.getCNPJ());
			super.setObject(fornecedor);
//			this.tfDtNasc.setText(fornecedor.getDataNascimentoString());
//			this.tfTelf.setText(fornecedor.getTelefone());
//			this.tfCelular.setText(fornecedor.getCelular());
//			this.tfLogradouro.setText(fornecedor.getLogradouro());
//			this.tfNumero.setText(fornecedor.getNumero());
//			this.tfBairro.setText(fornecedor.getBairro());
//			this.tfCidade.setText(fornecedor.getCidade());
//			this.tfEstado.setText(fornecedor.getEstado());
//			this.tfCEP.setText(fornecedor.getCep());
		}
	}
}
