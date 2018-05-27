package br.edu.udc.sistemas.poo2.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.FREE_MEM;

import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.session.SessionFuncionario;


public class FormLogin extends JInternalFrame{
	
	
	class event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Funcionario funcionario = validarLogin();
			if(funcionario != null ){
				FormMain.setFuncionarioSessao(funcionario, frm);
			}
		}		
	}
	
	
	public FormLogin(JFrame locMain) {
		super("Bem Vindo!");
		frm = (FormMain)locMain;
		this.pack();
		this.setEnabled(true);
		this.setVisible(true);
		this.setSize(640, 480);

		Dimension d = locMain.getSize();
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 

	    createButtonsPanel();
	    
	    

	}


	private static final long serialVersionUID = 1L;
	protected FormMain frm;
	protected JPanel fieldsPanel;
	private JButton btnLogin;
	private JTextField tfLogin;
	private JTextField tfSenha;
	
	protected void createButtonsPanel() {
		this.setLayout(new BorderLayout());

		this.fieldsPanel = new JPanel();
		this.add(fieldsPanel, BorderLayout.NORTH);
		
		this.tfSenha = new JTextField("");
		this.tfLogin = new JTextField();

		this.fieldsPanel.setLayout(new GridLayout(0, 3));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Login"));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(this.tfLogin);
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Senha"));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(this.tfSenha);
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		
		this.fieldsPanel.add(new JLabel(""));
		btnLogin = new JButton("Logar");
		
		this.fieldsPanel.add(btnLogin);

		btnLogin.addActionListener(new event() );
	}
	
	private Funcionario validarLogin() {
		SessionFuncionario sessionFuncionario = new SessionFuncionario();
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(this.tfLogin.getText());
		funcionario.setSenha(this.tfSenha.getText());
		
		try {
			funcionario = (Funcionario)sessionFuncionario.findByLogin(funcionario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Usuario/Senha Invalida!", "Mensagem", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return null;
		}
		
		if(funcionario != null) {
			JOptionPane.showMessageDialog(this, "Logado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
			this.setEnabled(false);
			this.setVisible(false);
			return funcionario;
		}
		
		return null;

	}
	
	
	
	
	

}
