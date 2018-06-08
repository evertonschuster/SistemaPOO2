package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNota;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;
import br.edu.udc.sistemas.poo2.session.SessionNota;
import br.edu.udc.sistemas.poo2.session.SessionProduto;

public class FormFindNota extends FormFind {

	private static final long serialVersionUID = 1L;

	protected JTextField tfIdNota;
	protected JTextField tfDescricao;
	protected JTextField tfnumeroDaNota;
	protected JFormattedTextField tfData;
	protected JComboBox<Object>  cmbFuncionario;
	
	protected TableModelNota tableModelNota;

	@Override
	protected void createFieldsPanel() {
		this.tfIdNota = new JTextField();
		this.tfDescricao = new JTextField();
		this.tfnumeroDaNota = new JTextField();
		try {
			this.tfData = new JFormattedTextField(new MaskFormatter("##/##/####"));
			this.tfData.setColumns(6);
			this.tfData.setValue(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SessionFuncionario sessioFuncionario = new SessionFuncionario();
		try {
			this.cmbFuncionario = new JComboBox<>(sessioFuncionario.find(new Funcionario()));
			this.cmbFuncionario.insertItemAt("Selecione" , 0);
			this.cmbFuncionario.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Funcionarios ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdNota);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Descricao:"));
		this.fieldsPanel.add(this.tfDescricao);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Numero Da Nota:"));
		this.fieldsPanel.add(this.tfnumeroDaNota);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Data:"));
		this.fieldsPanel.add(this.tfData);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Funcionario:"));
		this.fieldsPanel.add(this.cmbFuncionario);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));

		this.fieldsPanel.add(new JLabel(""));
	}

	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelNota = new TableModelNota();
		this.list.setModel(this.tableModelNota);
	}

	@Override
	protected boolean validateFields() {
		return true;
	}

	@Override
	protected void find() throws Exception {
		Nota nota = new Nota();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
			nota.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			nota.setDescricao(null);
		} else {
			nota.setDescricao(this.tfDescricao.getText());
		}
		
		if(this.tfnumeroDaNota.getText().trim().isEmpty()) {
			nota.setNumeroNota(null);
		}else {
			nota.setNumeroNota( Integer.parseInt(this.tfnumeroDaNota.getText()) );
		}
		
		if(this.tfData.getText().trim().contains(" ")) {
			nota.setData( null);
		}else {
			try {
				IOTools.validaData(this.tfData.getText());
				nota.setData( sdf.parse(this.tfData.getText()) );
			}catch (Exception e) {
				nota.setData( null);// TODO: handle exception
			}
		}
		
		if(this.cmbFuncionario.getSelectedItem() instanceof Funcionario) {
			nota.setFuncionario((Funcionario)this.cmbFuncionario.getSelectedItem());
		}else {
			nota.setFuncionario(null);
		}
		
		

		SessionNota sessionNota = new SessionNota();
		this.tableModelNota.setList(sessionNota.find(nota));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdNota.setText("");
		this.tfDescricao.setText("");
		this.tableModelNota.setList(new Object[0]);
		this.tfnumeroDaNota.setText("");
		this.tfData.setText("");
		this.cmbFuncionario.setSelectedIndex(0);

	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Nota");
		this.getInternalFrame().setContentPane(new FormCreateNota());
	}

	@Override
	protected void detail() throws Exception {
		Nota nota = (Nota) this.tableModelNota.getList()[this.list.getSelectedRow()];
		FormCreateNota formManterNota = new FormCreateNota();
		formManterNota.setObject(nota);
		getInternalFrame().setTitle("Manter Nota");
		getInternalFrame().setContentPane(formManterNota);
	}

}
