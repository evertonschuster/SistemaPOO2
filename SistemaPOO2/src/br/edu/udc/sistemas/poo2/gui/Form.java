package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public abstract class Form extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JPanel fieldsPanel;
	protected JPanel buttonsPanel;

	public Form() {
		this.setLayout(new BorderLayout());

		this.fieldsPanel = new JPanel();
		this.add(fieldsPanel, BorderLayout.NORTH);
		this.createFieldsPanel();

		this.buttonsPanel = new JPanel();
		this.buttonsPanel.setLayout(new FlowLayout());
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.createButtonsPanel();
	}

	protected JInternalFrame getInternalFrame() {
		return (JInternalFrame) this.getParent().getParent().getParent();
	}

	protected abstract void createFieldsPanel();

	protected abstract void createButtonsPanel();

	protected abstract boolean validateFields();
}