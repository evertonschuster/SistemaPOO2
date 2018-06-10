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
	protected JFormattedTextField tfCNPJ;

	@Override
	protected void createFieldsPanel() {
		this.tfNomeFantazia = new JTextField();
		this.tfrazaoSocial = new JTextField();
		try {
			this.tfCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
			this.tfCNPJ.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}


		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		
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
		
		super.createFieldsPanel();
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

		fornecedor.setNomeFantazia(this.tfNomeFantazia.getText().trim());
		fornecedor.setRazaoSocial(this.tfrazaoSocial.getText().trim());
		fornecedor.setCNPJ(this.tfCNPJ.getText().trim().replaceAll("[.-/]","").trim());
		fornecedor.setDataNascimento( sdf.parse(this.tfDtNasc.getText().trim().replaceAll("[.-]","").trim()) );
		fornecedor.setTelefone(this.tfTelf.getText().trim().replaceAll("[.-]","").replaceAll("[()]",""));
		fornecedor.setCelular(this.tfCelular.getText().trim().replaceAll("[.-]","").replaceAll("[()]",""));
		fornecedor.setLogradouro(this.tfLogradouro.getText().trim().replaceAll("[.-]",""));
		fornecedor.setNumero(this.tfNumero.getText().trim().replaceAll("[.-]",""));
		fornecedor.setBairro(this.tfBairro.getText().trim().replaceAll("[.-]",""));
		fornecedor.setCidade(this.tfCidade.getText().trim().replaceAll("[.-]",""));
		fornecedor.setEstado(this.tfEstado.getText().trim().replaceAll("[.-]",""));
		fornecedor.setCep(this.tfCEP.getText().trim().replaceAll("[.-]",""));
		
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
		}
	}
}
