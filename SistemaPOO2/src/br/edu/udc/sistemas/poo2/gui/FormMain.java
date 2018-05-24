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
				
			}
		}
	}

	private JInternalFrame internalMarca = new JInternalFrame("Consultar Marca", true, false, true, true);
	private JInternalFrame internalModelo = new JInternalFrame("Consultar Modelo", true, false, true, true);
	private JInternalFrame internalCliente = new JInternalFrame("Consultar Cliente", true, false, true, true);
	private JInternalFrame internalVeiculo = new JInternalFrame("Consultar Veiculo", true, false, true, true);
	private JInternalFrame internalProduto = new JInternalFrame("Consultar Produto", true, false, true, true);
	private JInternalFrame internalServico = new JInternalFrame("Consultar Servico", true, false, true, true);

	private JMenu menuExit;
	private JMenuItem itemMarca;
	private JMenuItem itemModelo;
	private JMenuItem itemCliente;
	private JMenuItem itemVeiculo;
	private JMenuItem itemProduto;
	private JMenuItem itemServico;

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

		this.itemModelo = new JMenuItem("Modelo");
		this.itemModelo.setMnemonic('O');
		
		this.itemCliente = new JMenuItem("Cliente");
		this.itemCliente.setMnemonic('T');

		this.itemVeiculo = new JMenuItem("Veiculo");
		this.itemVeiculo.setMnemonic('V');
		
		this.itemProduto = new JMenuItem("Produto");
		this.itemProduto.setMnemonic('P');
		
		this.itemServico = new JMenuItem("Servico");
		this.itemServico.setMnemonic('R');

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
		menuCad.add(this.itemProduto);
		menuCad.add(this.itemServico);

		EventManager ev = new EventManager();
		this.menuExit.addMouseListener(ev);

		this.itemMarca.addActionListener(ev);
		this.itemModelo.addActionListener(ev);
		this.itemCliente.addActionListener(ev);
		this.itemVeiculo.addActionListener(ev);
		this.itemProduto.addActionListener(ev);
		this.itemServico.addActionListener(ev);

		//sdfsdf
		
	}

	public FormMain() {
		super("Sistema de Controle de Estoque");

		

		this.internal = new JInternalFrame("Bem Vindo!", true, false, true, true);
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

		this.setSize(640, 480);

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
		
		internalVeiculo.setContentPane(new FormFindVeiculo());
		internalVeiculo.pack();
		internalVeiculo.setEnabled(false);
		
		internalProduto.setContentPane(new FormFindProduto());
		internalProduto.pack();
		internalProduto.setEnabled(false);
		
		internalServico.setContentPane(new FormFindServico());
		internalServico.pack();
		internalServico.setEnabled(false);
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
		mainFrame.add(itemFrame);
		
		try {
			itemFrame.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}