package br.edu.udc.sistemas.poo2.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FormMain extends JFrame {
    // Controla a vers�o da classe para serializa��o
    private static final long serialVersionUID = 1L;

    private class EventManager implements MouseListener, ActionListener {
	@Override
	public void mouseClicked(MouseEvent e) {
	    if (e.getSource().equals(menuExit)) {
		if (JOptionPane.showConfirmDialog(null, "Sair do Sistema?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
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
		internal.setTitle("Consultar Marca");
		internal.setContentPane(new FormFindMarca());
	    } else if (e.getSource().equals(itemModelo)) {
		internal.setTitle("Consultar Modelo");
		internal.setContentPane(new FormFindModelo());
	    } else if (e.getSource().equals(itemCliente)) {
		internal.setTitle("Consultar Cliente");
		internal.setContentPane(new FormFindCliente());
	    } else if (e.getSource().equals(itemVeiculo)) {
	    internal.setTitle("Consultar Veiculo");
	    internal.setContentPane(new FormFindVeiculo());
	    } else if (e.getSource().equals(itemProduto)) {
		internal.setTitle("Consultar Produto");
		internal.setContentPane(new FormFindProduto());
	    } else if (e.getSource().equals(itemServico)) {
	    internal.setTitle("Consultar Servico");
		internal.setContentPane(new FormFindServico());
		}
	    
	}
    }
    

    private JMenu menuExit;
    private JMenuItem itemMarca;
    private JMenuItem itemModelo;
    private JMenuItem itemCliente;
    private JMenuItem itemVeiculo;
    private JMenuItem itemProduto;
    private JMenuItem itemServico;

    private JInternalFrame internal;

    private void createMenus() {
	JMenuBar menuBar = new JMenuBar();

	JMenu menuCad = new JMenu("Cadastro");
	menuCad.setMnemonic('C');
	this.menuExit = new JMenu("Sair");
	this.menuExit.setMnemonic('S');

	this.itemMarca = new JMenuItem("Marca");
	this.itemMarca.setMnemonic('M');

	this.itemModelo = new JMenuItem("Modelo");
	this.itemModelo.setMnemonic('o');

	this.itemCliente = new JMenuItem("Cliente");
	this.itemCliente.setMnemonic('e');
	
	this.itemVeiculo = new JMenuItem("Veiculo");
	this.itemVeiculo.setMnemonic('V');
	
	this.itemProduto = new JMenuItem("Produto");
	this.itemProduto.setMnemonic('p');
	
	this.itemServico = new JMenuItem("Servico");
	this.itemServico.setMnemonic('r');

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
	
    }

    public FormMain() {
	super("Sistema de Controle de Estoque");


	JDesktopPane mainFrame = new JDesktopPane();

	this.internal = new JInternalFrame("Bem Vindo!", true, false, true, true);
	this.internal.pack();
	this.internal.setEnabled(false);

	this.createMenus();
	
	// Efetuo o controle de excess�es para tratamento
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
    }
}