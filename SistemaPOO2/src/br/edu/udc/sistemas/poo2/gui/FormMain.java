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
    // Controla a versão da classe para serialização
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
		internal.setTitle("Consultar Modelo");
	    } else if (e.getSource().equals(itemVeiculo)) {
		// internal.setContentPane(new FormConsultarVeiculo());
		internal.setTitle("Consultar Veículo");
	    }
	}
    }

    private JMenu menuExit;
    private JMenuItem itemMarca;
    private JMenuItem itemModelo;
    private JMenuItem itemVeiculo;

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

	this.itemVeiculo = new JMenuItem("veículo");
	this.itemVeiculo.setMnemonic('V');

	// Adiciono a barra de menus no JFrame
	this.setJMenuBar(menuBar);

	// Adiciono os menus Cadastro e Sair na barra de menus
	menuBar.add(menuCad);
	menuBar.add(this.menuExit);

	// Adiciono os itens de menu dentro do menu de cadastro
	menuCad.add(this.itemMarca);
	menuCad.add(this.itemModelo);
	menuCad.add(this.itemVeiculo);

	EventManager ev = new EventManager();
	this.menuExit.addMouseListener(ev);

	this.itemMarca.addActionListener(ev);
	this.itemModelo.addActionListener(ev);
	this.itemVeiculo.addActionListener(ev);
	
    }

    public FormMain() {
	super("Sistema de Controle de Estoque");


	JDesktopPane mainFrame = new JDesktopPane();

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
    }
}