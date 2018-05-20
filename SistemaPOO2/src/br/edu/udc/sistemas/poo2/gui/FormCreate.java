package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class FormCreate extends Form {

	private static final long serialVersionUID = 1L;

	private class EventManager implements MouseListener {

		private JPanel parentForm;

		public EventManager(JPanel parentForm) {
			this.parentForm = parentForm;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if ((e.getSource().equals(btSave)) && (validateFields())) {
					save();
					JOptionPane.showMessageDialog(this.parentForm, "Salvo com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				} else if (e.getSource().equals(btDelete)) {
					if (JOptionPane.showConfirmDialog(this.parentForm, "Deseja excluir?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						remove();
					}
				} else if (e.getSource().equals(btClean)) {
					clean();
				} else if (e.getSource().equals(btBack)) {
					goFind();
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

	private JButton btSave;
	private JButton btDelete;
	private JButton btClean;
	private JButton btBack;

	@Override
	protected void createButtonsPanel() {
		this.btSave = new JButton("Salvar");
		this.btDelete = new JButton("Excluir");
		this.btClean = new JButton("Limpar");
		this.btBack = new JButton("Voltar");

		EventManager event = new EventManager(this);
		this.btSave.addMouseListener(event);

		this.btDelete.addMouseListener(event);
		this.btClean.addMouseListener(event);
		this.btBack.addMouseListener(event);

		this.buttonsPanel.add(this.btSave);
		this.buttonsPanel.add(this.btDelete);
		this.buttonsPanel.add(this.btClean);
		this.buttonsPanel.add(this.btBack);

		this.add(this.buttonsPanel, BorderLayout.SOUTH);
	}

	protected abstract void save() throws Exception;

	protected abstract void remove() throws Exception;

	protected abstract void clean() throws Exception;

	protected abstract void goFind() throws Exception;

	protected abstract void setObject(Object object) throws Exception;
}