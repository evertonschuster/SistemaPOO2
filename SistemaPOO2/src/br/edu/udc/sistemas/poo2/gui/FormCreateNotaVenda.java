package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
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
import br.edu.udc.sistemas.poo2.entity.ListaDeServico;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.entity.Servico;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.gui.FormCreateNota.EventManager;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelListaDeProdutoServico;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelListaDeServicos;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionListaDeProduto;
import br.edu.udc.sistemas.poo2.session.SessionListaDeServico;
import br.edu.udc.sistemas.poo2.session.SessionNotaVenda;
import br.edu.udc.sistemas.poo2.session.SessionServico;
import br.edu.udc.sistemas.poo2.session.SessionVeiculo;

public class FormCreateNotaVenda extends FormCreateNota {

protected JComboBox<Object> cmbListadeCliente;
protected JComboBox<Object> cmbListadeVeiculo;
protected JComboBox<Object> cmbListadeServico;


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
			this.cmbListadeServico.addItemListener(new EventManager(this) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Veiculo ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		

		
		this.fieldsPanel.add(new JLabel("Servico:"));
		this.fieldsPanel.add(this.cmbListadeServico);
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Cliente:"),20);
		this.fieldsPanel.add(this.cmbListadeCliente, 21);
		this.fieldsPanel.add(new JLabel(""),22);
		this.fieldsPanel.add(new JLabel(""),23);
		
		this.fieldsPanel.add(new JLabel("Veiculo:"),24);
		this.fieldsPanel.add(this.cmbListadeVeiculo, 25);
		this.fieldsPanel.add(new JLabel(""),26);
		this.fieldsPanel.add(new JLabel(""),27);


		this.cmbListadeCliente.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		this.cmbListadeVeiculo.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
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
		SessionListaDeServico sessionListaDeServico = new SessionListaDeServico();
		Object listaDeProdutos[] = this.tableProdutos.getList();
		for(int n = 0; n < listaDeProdutos.length; n++ ) {
			Object select = listaDeProdutos[n];
			if(select instanceof ListaDeProduto) {
				ListaDeProduto addLista = (ListaDeProduto)select;
				addLista.setNota(nota);
				sessionListaDeProdutos.save(addLista, n == listaDeProdutos.length -1);
				
			}else if(select instanceof ListaDeServico) {
				ListaDeServico addLista = (ListaDeServico)select;
				addLista.setNota(nota);
				sessionListaDeServico.save(addLista, n == listaDeProdutos.length -1);
				
			}
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
			
			SessionListaDeServico sessionServico = new SessionListaDeServico();
			ListaDeServico listaDeServico = new ListaDeServico();
			listaDeServico.setNota(nota);
			try {
				for(Object obj : sessionServico.find(listaDeServico )) {
					this.tableProdutos.addProduto(obj);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void selectedGrid(Object sender) {
		Object selected =  this.tableProdutos.getList()[this.list.getSelectedRow()];
		if(selected instanceof ListaDeProduto || selected instanceof ListaDeServico) {
			super.selectedGrid(sender);
		}
			
	}
	protected void addProduto(Object sender) {
		if(this.cmbListadeProdutos.getSelectedIndex() != 0) {
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
			
			this.tableProdutos.addProduto(lp);
			this.cmbListadeProdutos.setSelectedIndex(0);
			this.cmbListadeServico.setSelectedIndex(0);
		}else if(this.cmbListadeServico.getSelectedIndex() != 0){
			
			Object s = this.cmbListadeServico.getSelectedItem();
			if(!(s instanceof Servico)) {
				JOptionPane.showMessageDialog(this, "Selecione um Servico!", "Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Servico ser = (Servico) s;
			
			ListaDeServico lp = new ListaDeServico();
			lp.setServico(ser);
			lp.setNota(new Nota());
			tableProdutos.addProduto(lp);
			this.cmbListadeProdutos.setSelectedIndex(0);
			this.cmbListadeServico.setSelectedIndex(0);
		}
	}
	
	protected void  controlaEvento(Object sender) {
		/*if (sender.equals(btnAddServico)) {
			if(((JButton)sender).isEnabled()) {
				addProduto(sender);
			}
			
		}else if (sender.equals(btnRemoveServico)) {
			if(((JButton)sender).isEnabled()) {
				
				ListaDeServico selected = (ListaDeServico) this.tableProdutos.getList()[this.list.getSelectedRow()];
				this.tableProdutos.removeProduto(selected);
				
				SessionListaDeServico sessionListaDeServico = new SessionListaDeServico();
				try {
					sessionListaDeServico.remove(selected,false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.btnRemoveServico.setEnabled(false);
			}
		}*/
	}
	
	protected void removeProduto(Object sender) {
		
		if(this.tableProdutos.getList()[this.list.getSelectedRow()] instanceof ListaDeProduto) {
			super.removeProduto(sender);
			this.cmbListadeProdutos.setSelectedIndex(0);
			this.cmbListadeServico.setSelectedIndex(0);
			return;
		}
		
		ListaDeServico selected = (ListaDeServico) this.tableProdutos.getList()[this.list.getSelectedRow()];
		this.tableProdutos.removeProduto(selected);
		
		SessionListaDeServico sessionListaDeServico = new SessionListaDeServico();
		try {
			sessionListaDeServico.remove(selected,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cmbListadeProdutos.setSelectedIndex(0);
		this.cmbListadeServico.setSelectedIndex(0);
		this.btnRemoveProduto.setEnabled(false);
	}
	
	protected void selectCombo(Object sender) {
		try {
			
			if((this.cmbListadeProdutos.getSelectedIndex() == 0) && (this.cmbListadeServico.getSelectedIndex() == 0) ){
				return;
			}
			/*Object obj = cmbListadeServico.getSelectedItem();
			if((obj instanceof Servico) || ( obj instanceof ListaDeServico)) {
				list.clearSelection();
				btnAddProduto.setEnabled(false);
				btnRemoveProduto.setEnabled(false);
			}else {
				super.selectCombo(sender);

			}*/
			
			this.list.clearSelection();
			this.btnAddProduto.setEnabled(true);
			this.btnRemoveProduto.setEnabled(false);
			if(sender.equals(this.cmbListadeServico)) {
				this.tfqndProduto.setVisible(false);
				this.lblQndProduto.setVisible(false);
				this.cmbListadeProdutos.setSelectedIndex(0);
				//this.fieldsPanel.add(buttonsPanelProduto,34);
				
			}else if(sender.equals(this.cmbListadeProdutos)) {
				this.tfqndProduto.setVisible(true);
				this.lblQndProduto.setVisible(true);
				this.cmbListadeServico.setSelectedIndex(0);
				//this.fieldsPanel.remove(buttonsPanelProduto);
				//this.fieldsPanel.add(buttonsPanelProduto,30);
			}
			
			
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
