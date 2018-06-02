package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.udc.sistemas.poo2.entity.Contribuinte;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.ListaDeProduto;
import br.edu.udc.sistemas.poo2.entity.Nota;
import br.edu.udc.sistemas.poo2.entity.Produto;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelListaDeProdutos;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelProduto;
import br.edu.udc.sistemas.poo2.infra.ExceptionValidacao;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;
import br.edu.udc.sistemas.poo2.session.SessionListaDeProduto;
import br.edu.udc.sistemas.poo2.session.SessionNota;
import br.edu.udc.sistemas.poo2.session.SessionProduto;

public class FormCreateNota extends FormCreate {
	
	private class EventManager implements MouseListener, ItemListener  {
		private JPanel parentForm;

		public EventManager(JPanel parentForm) {
			this.parentForm = parentForm;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if (e.getSource().equals(btnAddProduto)) {
					if(((JButton)e.getSource()).isEnabled()) {
						addProduto();
					}
					
				}else if (e.getSource().equals(btnRemoveProduto)) {
					if(((JButton)e.getSource()).isEnabled()) {
						removeProduto();
					}
				}else if (e.getSource().equals(list)) {
					btnRemoveProduto.setEnabled(true);
					btnAddProduto.setEnabled(false);
					cmbListadeProdutos.setSelectedIndex(0);
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this.parentForm, e2.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			}
		}


		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			try {
				Object obj = cmbListadeProdutos.getSelectedItem();
				if(obj instanceof Produto) {
					list.clearSelection();
					btnAddProduto.setEnabled(true);
					btnRemoveProduto.setEnabled(false);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	private static final long serialVersionUID = 1L;

	protected JTextField tfIdNota;
	protected JTextField tfDescricao;
	protected JTextField tfnumeroDaNota;
	protected JFormattedTextField tfData;
	protected JComboBox<Object>  cmbFuncionario;
	protected JComboBox<Object> cmbListadeProdutos;
	
	protected JPanel buttonsPanelProduto;
	protected JTextField tfqndProduto;
	protected JButton btnAddProduto;
	protected JButton btnRemoveProduto;
	
	protected JScrollPane findPanel;
	protected JTable list;
	protected TableModelListaDeProdutos tableProdutos;
		

	@Override
	protected void createFieldsPanel() {
		this.tfIdNota = new JTextField();
		this.tfIdNota.setEnabled(false);
		this.tfIdNota.setEditable(false);
		this.tfDescricao = new JTextField();
		this.tfnumeroDaNota = new JTextField();
		
		this.buttonsPanelProduto = new JPanel();
		this.buttonsPanelProduto.setLayout(new BoxLayout(this.buttonsPanelProduto, BoxLayout.X_AXIS));
		
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
		
		SessionProduto sessionProduto = new SessionProduto();
		
		try {
			this.cmbListadeProdutos = new JComboBox<>(sessionProduto.find(new Produto()));
			this.cmbListadeProdutos.insertItemAt("Selecione" , 0);
			this.cmbListadeProdutos.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Produtos ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		
		
		this.fieldsPanel.setLayout(new GridLayout(0, 4));
		
		this.fieldsPanel.add(new JLabel("Codigo:"));
		this.fieldsPanel.add(this.tfIdNota);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel("Descrição:"));
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
		
		this.fieldsPanel.add(new JLabel("Produtos:"));
		this.fieldsPanel.add(this.cmbListadeProdutos);
		this.fieldsPanel.add(buttonsPanelProduto, BorderLayout.EAST);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		
		this.tfqndProduto = new JTextField();
		this.btnAddProduto = new JButton("Adicionar");
		this.btnRemoveProduto = new JButton("Remover");
		this.tfqndProduto.setColumns(6);
		this.buttonsPanelProduto.add(new JLabel("  Quantidade:     "));
		this.buttonsPanelProduto.add(this.tfqndProduto);
		this.buttonsPanelProduto.add(new JLabel("  "));
		this.buttonsPanelProduto.add(this.btnAddProduto);
		this.buttonsPanelProduto.add(new JLabel("  "));
		this.buttonsPanelProduto.add(this.btnRemoveProduto);
		
		this.btnAddProduto.setEnabled(false);
		this.btnRemoveProduto.setEnabled(false);

		this.list = new JTable();
		this.findPanel = new JScrollPane(this.list);
		this.add(this.findPanel, BorderLayout.CENTER);
		this.tableProdutos = new TableModelListaDeProdutos();
		this.list.setModel(this.tableProdutos);
				
		EventManager evento = new EventManager(this);
		this.btnAddProduto.addMouseListener(evento);
		this.btnRemoveProduto.addMouseListener(evento);
		this.list.addMouseListener(evento);
		this.cmbListadeProdutos.addItemListener(evento);
	}

	@Override
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Descriçâo Inválida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			this.tfDescricao.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Nota nota = new Nota();

		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
		}
		
		nota.setDescricao(this.tfDescricao.getText());
		nota.setNumeroNota(Integer.parseInt(this.tfnumeroDaNota.getText()) );
		nota.setFuncionario((Funcionario)this.cmbFuncionario.getSelectedItem());
		nota.setData( new Date() );
		
		SessionNota sessionNota = new SessionNota();
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
		Nota nota = new Nota();
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
		}
		SessionNota sessionNota = new SessionNota();
		sessionNota.remove(nota);
		this.goFind();
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdNota.setText("");
		this.tfDescricao.setText("");
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Nota");
		this.getInternalFrame().setContentPane(new FormFindNota());
		
		SessionListaDeProduto ses = new SessionListaDeProduto();
		ses.rollBack();
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Nota) {
			Nota nota = (Nota) object;
			this.tfIdNota.setText(String.valueOf(nota.getId()));
			this.tfDescricao.setText(nota.getDescricao());
			
			SessionListaDeProduto sessionProdutos = new SessionListaDeProduto();
			ListaDeProduto listaDeProduto = new ListaDeProduto();
			listaDeProduto.setNota(nota);
			try {
				this.tableProdutos.setList(sessionProdutos.find(listaDeProduto ));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addProduto() {
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
		
		if(p.getQtd() <= Integer.parseInt(this.tfqndProduto.getText())) {
			JOptionPane.showMessageDialog(this, "Estoque insuficiente!,\nEm estoque: " + p.getQtd(), "Aviso!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		ListaDeProduto lp = new ListaDeProduto();
		lp.setProduto(p);
		lp.setNota(new Nota());
		lp.setQnt( Integer.parseInt(this.tfqndProduto.getText()) ); 
		tableProdutos.addProduto(lp);

	}
	
	private void removeProduto() {
		ListaDeProduto selected = (ListaDeProduto) this.tableProdutos.getList()[this.list.getSelectedRow()];
		this.tableProdutos.removeProduto(selected);
		
		SessionListaDeProduto sessionListaDeProduto = new SessionListaDeProduto();
		try {
			sessionListaDeProduto.remove(selected,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.btnRemoveProduto.setEnabled(false);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
