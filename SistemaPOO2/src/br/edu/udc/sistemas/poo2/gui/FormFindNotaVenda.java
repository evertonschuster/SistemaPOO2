package br.edu.udc.sistemas.poo2.gui;

import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;
import br.edu.udc.sistemas.poo2.entity.Veiculo;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNotaCompta;
import br.edu.udc.sistemas.poo2.gui.tableModel.TableModelNotaVenda;
import br.edu.udc.sistemas.poo2.infra.IOTools;
import br.edu.udc.sistemas.poo2.session.SessionCliente;
import br.edu.udc.sistemas.poo2.session.SessionNotaVenda;
import br.edu.udc.sistemas.poo2.session.SessionVeiculo;

public class FormFindNotaVenda extends FormFindNota {

	protected JComboBox<Object> cmbListadeCliente;
	protected JComboBox<Object> cmbListadeVeiculo;
	
	@Override
	protected void createFieldsPanel() {
		super.createFieldsPanel();
		
		try {
			SessionCliente sessioCliente = new SessionCliente();
			this.cmbListadeCliente = new JComboBox<>(sessioCliente.find(new Cliente()));
			this.cmbListadeCliente.insertItemAt("Selecione" , 0);
			this.cmbListadeCliente.setSelectedIndex(0);
			this.cmbListadeCliente.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));

			SessionVeiculo sessioVeiculo = new SessionVeiculo();
			this.cmbListadeVeiculo = new JComboBox<>(sessioVeiculo.find(new Veiculo()));
			this.cmbListadeVeiculo.insertItemAt("Selecione" , 0);
			this.cmbListadeVeiculo.setSelectedIndex(0);
			this.cmbListadeVeiculo.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Nao foi possivel carregar os Veiculo ","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		
		this.fieldsPanel.add(new JLabel("Cliente:"),20);
		this.fieldsPanel.add(this.cmbListadeCliente, 21);
		this.fieldsPanel.add(new JLabel(""),22);
		this.fieldsPanel.add(new JLabel(""),23);
		
		this.fieldsPanel.add(new JLabel("Veiculo:"),24);
		this.fieldsPanel.add(this.cmbListadeVeiculo, 25);
		this.fieldsPanel.add(new JLabel(""),26);
		this.fieldsPanel.add(new JLabel(""),27);

	}
	
	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModelNota = new TableModelNotaVenda();
		this.list.setModel(this.tableModelNota);
	}

	@Override
	protected void find() throws Exception {
		NotaVenda nota = new NotaVenda();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			nota.setId(Integer.parseInt(this.tfIdNota.getText()));
		} catch (Exception e) {
			nota.setId(null);
		}

		if (this.tfDescricao.getText().trim().isEmpty()) {
			nota.setDescricao(null);
		} else {
			nota.setDescricao(this.tfDescricao.getText());
		}
		
		if(this.tfnumeroDaNota.getText().trim().isEmpty()) {
			nota.setNumeroNota(null);
		}else {
			nota.setNumeroNota( Integer.parseInt(this.tfnumeroDaNota.getText()) );
		}
		
		if(this.tfData.getText().trim().contains(" ")) {
			nota.setData( null);
		}else {
			try {
				IOTools.validaData(this.tfData.getText());
				nota.setData( sdf.parse(this.tfData.getText()) );
			}catch (Exception e) {
				nota.setData( null);// TODO: handle exception
			}
		}
		
		if(this.cmbFuncionario.getSelectedItem() instanceof Funcionario) {
			nota.setFuncionario((Funcionario)this.cmbFuncionario.getSelectedItem());
		}else {
			nota.setFuncionario(null);
		}
		
		SessionNotaVenda sessionNota = new SessionNotaVenda();
		this.tableModelNota.setList(sessionNota.find(nota));
	}
	
	@Override
	protected void clean() throws Exception {
		super.clean();
		this.cmbListadeCliente.setSelectedIndex(0);
		this.cmbListadeVeiculo.setSelectedIndex(0);
	}

	@Override
	protected void goNew() throws Exception {
		this.getInternalFrame().setTitle("Manter Nota Venda");
		this.getInternalFrame().setContentPane(new FormCreateNotaVenda());
	}

	@Override
	protected void detail() throws Exception {
		NotaVenda nota = (NotaVenda) this.tableModelNota.getList()[this.list.getSelectedRow()];
		FormCreateNotaVenda formManterNota = new FormCreateNotaVenda();
		formManterNota.setObject(nota);
		getInternalFrame().setTitle("Manter Nota Venda");
		getInternalFrame().setContentPane(formManterNota);
	}
	
	
}
