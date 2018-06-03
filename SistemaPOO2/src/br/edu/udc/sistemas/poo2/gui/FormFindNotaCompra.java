package br.edu.udc.sistemas.poo2.gui;

import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.NotaCompra;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNota;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNotaCompta;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFornecedor;
import br.edu.udc.sistemas.poo2.session.SessionNota;
import br.edu.udc.sistemas.poo2.session.SessionNotaCompra;

public class FormFindNotaCompra extends FormFindNota {
	protected JComboBox<Object> cmbFornecedor;

	@Override
	protected void createFieldsPanel() {
		super.createFieldsPanel();
		SessionFornecedor sessioFornecedor = new SessionFornecedor();
		try {
			this.cmbFornecedor = new JComboBox<>(sessioFornecedor.find(new Fornecedor()));
			this.cmbFornecedor.insertItemAt("Selecione" , 0);
			this.cmbFornecedor.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Fornecedor ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
	
		JLabel label = new JLabel("Fornecedor:  ");
		this.fieldsPanel.add(label,20);
		label.setHorizontalAlignment(JLabel.RIGHT);
		this.fieldsPanel.add(this.cmbFornecedor, 21);
		this.fieldsPanel.add(new JLabel(""),22);
		this.fieldsPanel.add(new JLabel(""),23);
	}
	
	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelNota = new TableModelNotaCompta();
		this.list.setModel(this.tableModelNota);
	}

	@Override
	protected void find() throws Exception {
		NotaCompra nota = new NotaCompra();
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
		
		if(this.cmbFornecedor.getSelectedItem() instanceof Fornecedor) {
			nota.setFornecedor((Fornecedor)this.cmbFornecedor.getSelectedItem());
		}else {
			nota.setFornecedor(null);
		}
		
		SessionNotaCompra sessionNota = new SessionNotaCompra();
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
		this.getInternalFrame().setContentPane(new FormCreateNotaCompra());
	}

	@Override
	protected void detail() throws Exception {
		NotaCompra nota = (NotaCompra) this.tableModelNota.getList()[this.list.getSelectedRow()];
		FormCreateNotaCompra formManterNota = new FormCreateNotaCompra();
		formManterNota.setObject(nota);
		getInternalFrame().setTitle("Manter Nota Compra");
		getInternalFrame().setContentPane(formManterNota);
	}

}
