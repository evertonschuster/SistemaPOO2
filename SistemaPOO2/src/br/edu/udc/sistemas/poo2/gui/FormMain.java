package br.edu.udc.sistemas.poo2.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import br.edu.udc.sistemas.poo2.entity.Funcionario;

public class FormMain extends JFrame {
	// Controla a versão da classe para serialização
	private static final long serialVersionUID = 1L;
	
	private class EventManager implements MouseListener, ActionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(menuExit)) {
				if (JOptionPane.showConfirmDialog(null, "Sair do Sistema?", "Aviso", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(itemMarca)) {
				visibleJpanel(internalMarca);
			
			} else if (e.getSource().equals(itemModelo)) {
				visibleJpanel(internalModelo);
				
			} else if (e.getSource().equals(itemCliente)) {
				visibleJpanel(internalCliente);
				
			} else if (e.getSource().equals(itemVeiculo)) {
				visibleJpanel(internalVeiculo);
			
			} else if (e.getSource().equals(itemProduto)) {
				visibleJpanel(internalProduto);
			
			} else if (e.getSource().equals(itemServico)) {
				visibleJpanel(internalServico);
			
			} else if (e.getSource().equals(itemFuncionario)) {
				visibleJpanel(internalFuncionario);
			
			}else if (e.getSource().equals(itemFornecedor)) {
				visibleJpanel(internalFornecedor);
			}
		}

	}
	
	protected static Funcionario funcionarioSessao = null;

	private EventManager ev = new EventManager();

	private JInternalFrame internalMarca = new JInternalFrame("Consultar Marca", true, true, true, true);
	private JInternalFrame internalModelo = new JInternalFrame("Consultar Modelo", true, true, true, true);
	private JInternalFrame internalCliente = new JInternalFrame("Consultar Cliente", true, true, true, true);
	private JInternalFrame internalProduto = new JInternalFrame("Consultar Produto", true, true, true, true);
	private JInternalFrame internalServico = new JInternalFrame("Consultar Servico", true, true, true, true);
	private JInternalFrame internalVeiculo = new JInternalFrame("Consultar Veiculo", true, true, true);
	private JInternalFrame internalFuncionario = new JInternalFrame("Consultar Funcionario", true, true, true);
	private JInternalFrame internalFornecedor = new JInternalFrame("Consultar Fornecedor", true, true, true);
	//private JInternalFrame internalModelo = new JInternalFrame("Consultar Modelo", true, true, true, true);

	private JMenu menuExit;
	private JMenuItem itemMarca;
	private JMenuItem itemModelo;
	private JMenuItem itemCliente;
	private JMenuItem itemVeiculo;
	private JMenuItem itemProduto;
	private JMenuItem itemServico;
	private JMenuItem itemFuncionario;
	private JMenuItem itemFornecedor;

	private JInternalFrame internal;
	private JDesktopPane mainFrame = new JDesktopPane();

	private void createMenus() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuCad = new JMenu("Cadastro");
		menuCad.setMnemonic('C');
		this.menuExit = new JMenu("Sair");
		this.menuExit.setMnemonic('S');

		this.itemMarca = new JMenuItem("Marca");
		this.itemMarca.setMnemonic('M');
		
		this.itemCliente = new JMenuItem("Cliente");
		this.itemCliente.setMnemonic('C');

		this.itemModelo = new JMenuItem("Modelo");
		this.itemModelo.setMnemonic('M');
		
		this.itemVeiculo = new JMenuItem("Veiculo");
		this.itemVeiculo.setMnemonic('V');
		
		this.itemProduto = new JMenuItem("Produto");
		this.itemProduto.setMnemonic('P');

		this.itemServico = new JMenuItem("Servico");
		this.itemServico.setMnemonic('S');
		
		this.itemFuncionario = new JMenuItem("Funcionario");
		this.itemFuncionario.setMnemonic('F');
		
		this.itemFornecedor = new JMenuItem("Fornecedor");
		this.itemFornecedor.setMnemonic('F');

		// Adiciono a barra de menus no JFrame
		this.setJMenuBar(menuBar);

		// Adiciono os menus Cadastro e Sair na barra de menus
		menuBar.add(menuCad);
		menuBar.add(this.menuExit);

		// Adiciono os itens de menu dentro do menu de cadastro
		menuCad.add(this.itemMarca);
		menuCad.add(this.itemModelo);
		menuCad.add(this.itemCliente);
		menuCad.add(this.itemVeiculo);
		menuCad.add(this.itemServico);
		menuCad.add(this.itemProduto);
		menuCad.add(this.itemFuncionario);
		menuCad.add(this.itemFornecedor);
		

		this.menuExit.addMouseListener(ev);

		//eventos de MENUS
		this.itemMarca.addActionListener(ev);
		this.itemModelo.addActionListener(ev);
		this.itemCliente.addActionListener(ev);
		this.itemVeiculo.addActionListener(ev);
		this.itemServico.addActionListener(ev);
		this.itemProduto.addActionListener(ev);
		this.itemFuncionario.addActionListener(ev);
		this.itemFornecedor.addActionListener(ev);
	}
	

	public FormMain() {
		super("Sistema de Controle de Estoque");
		if(funcionarioSessao == null) {

			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.internal = new FormLogin(this);
			mainFrame.add(internal);
			this.getContentPane().add(mainFrame);
			
			try {
				this.internal.setSelected(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {

			iniciarSessao();
		}
		
	}
	
	public void iniciarSessao() {

		this.setTitle("Sistema de Controle de Estoque - Bem vindo " + funcionarioSessao.getNome());
		
		this.internal = new JInternalFrame("Bem Vindo!", true, true, true, true);
		this.internal.pack();
		this.internal.setEnabled(false);

		this.createMenus();

		// Efetuo o controle de excessões para tratamento
		try {
			this.internal.setMaximum(true);
		} catch (Exception e) {
		}

		this.internal.setVisible(true);
		mainFrame.add(internal);

		this.getContentPane().add(mainFrame);

		//this.setSize(640, 480);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		

		try {
			this.internal.setSelected(true);
		} catch (Exception e) {
		}

		// Apresentar na tela
		this.setVisible(true);

		// Configura para o programa encerrar ao fechar esta janela
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		internalMarca.setContentPane(new FormFindMarca());
		internalMarca.pack();
		internalMarca.setEnabled(false);
		
		internalModelo.setContentPane(new FormFindModelo());
		internalModelo.pack();
		internalModelo.setEnabled(false);
		
		internalCliente.setContentPane(new FormFindCliente());
		internalCliente.pack();
		internalCliente.setEnabled(false);
		
		internalServico.setContentPane(new FormFindServico());
		internalServico.pack();
		internalServico.setEnabled(false);
		
		internalProduto.setContentPane(new FormFindProduto());
		internalProduto.pack();
		internalProduto.setEnabled(false);
		
		internalVeiculo.setContentPane(new FormFindVeiculo());
		internalVeiculo.pack();
		internalVeiculo.setEnabled(false);
		
		internalFuncionario.setContentPane(new FormFindFuncionario());
		internalFuncionario.pack();
		internalFuncionario.setEnabled(false);
		
		internalFornecedor.setContentPane(new FormFindFornecedor());
		internalFornecedor.pack();
		internalFornecedor.setEnabled(false);
	}
	
	private void visibleJpanel(JInternalFrame itemFrame) {

		if(itemFrame.isVisible()) {
			itemFrame.moveToFront();
			
			itemFrame.pack();
			itemFrame.setEnabled(true);
			//Dimension resolucao = mainFrame.getSize();
			//itemFrame.setSize(resolucao);
			//itemFrame.setLocation(0, 0);
			
			return;
		}
		itemFrame.setVisible(true);
		itemFrame.moveToFront();
		itemFrame.setEnabled(true);
		itemFrame.pack();
		
		mainFrame.add(itemFrame);
		
		try {
			itemFrame.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public static Funcionario getFuncionarioSessao() {
		return funcionarioSessao;
	}

	public static void setFuncionarioSessao(Funcionario funcionarioSessao, Object frm) {
		FormMain.funcionarioSessao = funcionarioSessao;
		if((funcionarioSessao != null) && (frm != null)) {
			((FormMain)frm).iniciarSessao();
		}
	}

}