package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class FormFind extends Form {

	private static final long serialVersionUID = 1L;

	private class EventManager implements MouseListener {

		private JPanel parentForm;

		public EventManager(JPanel parentForm) {
			this.parentForm = parentForm;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if ((e.getSource().equals(btFind)) && (validateFields())) {
					find();
				} else if (e.getSource().equals(btNew)) {
					goNew();
				} else if (e.getSource().equals(btClean)) {
					clean();
				} else if (e.getSource().equals(list)) {
					detail();
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
	}

	protected JTable list;

	protected JButton btFind;
	protected JButton btNew;
	protected JButton btClean;

	protected JScrollPane findPanel;

	public FormFind() {
		super();
		this.createFindPanel();
	}

	protected void createFindPanel() {
		this.list = new JTable();
		this.list.addMouseListener(new EventManager(this));
		this.findPanel = new JScrollPane(this.list);
		this.add(this.findPanel, BorderLayout.CENTER);
	}

	@Override
	protected void createButtonsPanel() {
		this.btFind = new JButton("Consultar");
		this.btNew = new JButton("Novo");
		this.btClean = new JButton("Limpar");

		EventManager event = new EventManager(this);
		this.btClean.addMouseListener(event);
		this.btFind.addMouseListener(event);
		this.btNew.addMouseListener(event);

		this.buttonsPanel.add(this.btFind);
		this.buttonsPanel.add(this.btNew);
		this.buttonsPanel.add(this.btClean);
	}

	protected abstract void find() throws Exception;

	protected abstract void clean() throws Exception;

	protected abstract void goNew() throws Exception;

	protected abstract void detail() throws Exception;
}
