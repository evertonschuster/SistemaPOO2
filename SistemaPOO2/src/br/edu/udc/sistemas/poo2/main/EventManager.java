package br.edu.udc.sistemas.poo2.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JOptionPane;

public class EventManager implements MouseListener,ActionListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JMenu menu = (JMenu) arg0.getSource();
		JOptionPane.showMessageDialog(null,menu.getText(),"Alerta",JOptionPane.ERROR_MESSAGE);	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null,"Action","Alerta",JOptionPane.ERROR_MESSAGE);	
		
	}
	
}
