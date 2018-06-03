package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelListaDeProdutos;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelListaDeServicos;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionListaDeProduto;
import br.edu.udc.sistemas.poo2.session.SessionNotaVenda;
import br.edu.udc.sistemas.poo2.session.SessionServico;
import br.edu.udc.sistemas.poo2.session.SessionVeiculo;

public class FormCreateNotaVenda extends FormCreateNota {

protected JComboBox<Object> cmbListadeCliente;
protected JComboBox<Object> cmbListadeVeiculo;
protected JComboBox<Object> cmbListadeServico;

protected JButton btnAddServico;
protected JButton btnRemoveServico;
protected JTextField tfqndServico;
protected JPanel buttonsPanelServico;

protected JScrollPane findPanelServico;
protected JTable listServico;
protected TableModelListaDeServicos tableServico;

	@Override
	protected void createFieldsPanel() {
		super.createFieldsPanel();
		
		try {
			SessionCliente sessioCliente = new SessionCliente();
			this.cmbListadeCliente = new JComboBox<>(sessioCliente.find(new Cliente()));
			this.cmbListadeCliente.insertItemAt("Selecione" , 0);
			this.cmbListadeCliente.setSelectedIndex(0);

			SessionVeiculo sessioVeiculo = new SessionVeiculo();
			this.cmbListadeVeiculo = new JComboBox<>(sessioVeiculo.find(new Veiculo()));
			this.cmbListadeVeiculo.insertItemAt("Selecione" , 0);
			this.cmbListadeVeiculo.setSelectedIndex(0);
			
			SessionServico sessioServico = new SessionServico();
			this.cmbListadeServico = new JComboBox<>(sessioServico.find(new Servico()));
			this.cmbListadeServico.insertItemAt("Selecione" , 0);
			this.cmbListadeServico.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Veiculo ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		
		this.buttonsPanelServico = new JPanel();
		this.buttonsPanelServico.setLayout(new BoxLayout(this.buttonsPanelServico, BoxLayout.X_AXIS));
		
		this.fieldsPanel.add(new JLabel("Servico:"));
		this.fieldsPanel.add(this.cmbListadeServico);
		this.fieldsPanel.add(buttonsPanelServico);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		
		
		this.tfqndServico = new JTextField();
		this.btnAddServico = new JButton("Adicionar");
		this.btnRemoveServico = new JButton("Remover");
		this.tfqndServico.setColumns(6);
		this.buttonsPanelServico.add(new JLabel("  "));
		this.buttonsPanelServico.add(this.btnAddServico);
		this.buttonsPanelServico.add(new JLabel("  "));
		this.buttonsPanelServico.add(this.btnRemoveServico);
		
		this.btnAddServico.setEnabled(false);
		this.btnRemoveServico.setEnabled(false);
		
		
		this.fieldsPanel.add(new JLabel("Cliente:"),20);
		this.fieldsPanel.add(this.cmbListadeCliente, 21);
		this.fieldsPanel.add(new JLabel(""),22);
		this.fieldsPanel.add(new JLabel(""),23);
		
		this.fieldsPanel.add(new JLabel("Veiculo:"),24);
		this.fieldsPanel.add(this.cmbListadeVeiculo, 25);
		this.fieldsPanel.add(new JLabel(""),26);
		this.fieldsPanel.add(new JLabel(""),27);
		
		
		this.listServico = new JTable();
		this.findPanelServico = new JScrollPane(this.listServico);
		this.add(this.findPanelServico, BorderLayout.LINE_END);
		this.tableServico = new TableModelListaDeServicos();
		this.listServico.setModel(this.tableServico);
		

	}
	
	@Override
	protected boolean validateFields() {
		return super.validateFields();
		
	}
	
	@Override
	protected void save() throws Exception {
		NotaVenda nota = new NotaVenda();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
		}
		
		nota.setDescricao(this.tfDescricao.getText());
		nota.setNumeroNota(Integer.parseInt(this.tfnumeroDaNota.getText()) );
		nota.setFuncionario((Funcionario)this.cmbFuncionario.getSelectedItem());
		nota.setCliente((Cliente) this.cmbListadeCliente.getSelectedItem());
		nota.setVeiculo((Veiculo) this.cmbListadeVeiculo.getSelectedItem());
		nota.setData( sdf.parse(this.tfData.getText()) );
		nota.setTipoNota("venda");
		
		SessionNotaVenda sessionNota = new SessionNotaVenda();
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
		NotaVenda nota = new NotaVenda();
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
		}
		SessionNotaVenda sessionNota = new SessionNotaVenda();
		sessionNota.remove(nota);
		this.goFind();
	}
	
	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Nota Compra");
		this.getInternalFrame().setContentPane(new FormFindNotaVenda());
		
		SessionListaDeProduto ses = new SessionListaDeProduto();
		ses.rollBack();
	}
	
	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof NotaVenda) {
			super.setObject(object);
			NotaVenda nota = (NotaVenda) object;
			this.cmbListadeCliente.setSelectedItem(nota.getCliente());
			this.cmbListadeVeiculo.setSelectedItem(nota.getVeiculo());
		}
	}
	
	protected void addProduto() {
		Object s = this.cmbListadeProdutos.getSelectedItem();
		if(!(s instanceof Produto)) {
			JOptionPane.showMessageDialog(this, "Selecione um Produto!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Produto p = (Produto) s;
		if(this.tfqndProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe a Quantidade!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if( Integer.parseInt(this.tfqndProduto.getText())  < 0 ) {
			JOptionPane.showMessageDialog(this, "Informe a Quantidade Maior que '0'!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(p.getQtd() < Integer.parseInt(this.tfqndProduto.getText())) {
			JOptionPane.showMessageDialog(this, "Estoque insuficiente!,\nEm estoque: " + p.getQtd(), "Aviso!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		p.setQtd(p.getQtd() -  Integer.parseInt(this.tfqndProduto.getText()) );
		
		ListaDeProduto lp = new ListaDeProduto();
		lp.setProduto(p);
		lp.setNota(new Nota());
		lp.setQnt( Integer.parseInt(this.tfqndProduto.getText()) ); 
		tableProdutos.addProduto(lp);

	}
}
