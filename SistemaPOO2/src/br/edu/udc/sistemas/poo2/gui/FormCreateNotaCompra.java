package br.edu.udc.sistemas.poo2.gui;

import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.NotaCompra;
import br.edu.udc.sistemas.poo2.session.SessionFornecedor;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;
import br.edu.udc.sistemas.poo2.session.SessionListaDeProduto;
import br.edu.udc.sistemas.poo2.session.SessionNota;
import br.edu.udc.sistemas.poo2.session.SessionNotaCompra;

public class FormCreateNotaCompra extends FormCreateNota {

	protected JComboBox<Object> cmbListadeFornecedor;
	
	@Override
	protected void createFieldsPanel() {
		super.createFieldsPanel();
		
		SessionFornecedor sessioFornecedor = new SessionFornecedor();
		try {
			this.cmbListadeFornecedor = new JComboBox<>(sessioFornecedor.find(new Fornecedor()));
			this.cmbListadeFornecedor.insertItemAt("Selecione" , 0);
			this.cmbListadeFornecedor.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Fornecedor ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
	
		
		this.fieldsPanel.add(new JLabel("Fornecedor:"),20);
		this.fieldsPanel.add(this.cmbListadeFornecedor, 21);
		this.fieldsPanel.add(new JLabel(""),22);
		this.fieldsPanel.add(new JLabel(""),23);
		
		this.cmbListadeFornecedor.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		this.cmbFuncionario.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		this.cmbListadeProdutos.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
	}
	
	@Override
	protected boolean validateFields() {
		boolean superValido = super.validateFields();
		 
		if (superValido && !(this.cmbListadeFornecedor.getSelectedItem() instanceof Fornecedor)) {
			JOptionPane.showMessageDialog(this, "Selecione um Fornecedor!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.cmbListadeFornecedor.requestFocus();
			return false;
		}
		
		return superValido;
	}
	
	@Override
	protected void save() throws Exception {
		NotaCompra nota = new NotaCompra();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText().trim())) ;
		} catch (Exception e) {
		}
		
		nota.setDescricao(this.tfDescricao.getText().trim());
		nota.setNumeroNota(Integer.parseInt(this.tfnumeroDaNota.getText().trim()) );
		nota.setFuncionario((Funcionario)this.cmbFuncionario.getSelectedItem());
		nota.setFornecedor((Fornecedor) this.cmbListadeFornecedor.getSelectedItem());
		nota.setData( sdf.parse(this.tfData.getText().trim()) );
		nota.setTipoNota("compra");
		
		SessionNotaCompra sessionNota = new SessionNotaCompra();
		sessionNota.save(nota, false);
		
		SessionListaDeProduto sessionListaDeProdutos = new SessionListaDeProduto();
		Object listaDeProdutos[] = this.tableProdutos.getList();
		for(int n = 0; n < listaDeProdutos.length; n++ ) {
			ListaDeProduto addLista = (ListaDeProduto)listaDeProdutos[n];
			addLista.setNota(nota);
			sessionListaDeProdutos.save(addLista, n == listaDeProdutos.length -1);	
		}
			
		this.tfIdNota.setText(String.valueOf(nota.getId()));
	}

	@Override
	protected void remove() throws Exception {
		NotaCompra nota = new NotaCompra();
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
		}
		SessionNotaCompra sessionNota = new SessionNotaCompra();
		sessionNota.remove(nota);
		this.goFind();
	}
	
	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Nota Compra");
		this.getInternalFrame().setContentPane(new FormFindNotaCompra());
		
		SessionListaDeProduto ses = new SessionListaDeProduto();
		ses.rollBack();
	}
	
	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof NotaCompra) {
			super.setObject(object);
			NotaCompra nota = (NotaCompra) object;
			this.cmbListadeFornecedor.setSelectedItem(nota.getFornecedor());
		}
	}
}
