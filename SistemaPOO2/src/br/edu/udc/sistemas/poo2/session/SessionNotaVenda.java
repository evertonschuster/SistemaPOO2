package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOCliente;
import br.edu.udc.sistemas.poo2.dao.DAOFuncionario;
import br.edu.udc.sistemas.poo2.dao.DAONotaVenda;
import br.edu.udc.sistemas.poo2.dao.DAOVeiculo;
import br.edu.udc.sistemas.poo2.entity.Cliente;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.NotaVenda;
import br.edu.udc.sistemas.poo2.entity.Veiculo;

public class SessionNotaVenda extends SessionNota {
	public SessionNotaVenda() {
		this.dao = new DAONotaVenda();
	}
	
	@Override
	public Object[] find(Object obj) throws Exception {
		Object listnota[] = this.dao.find(obj);
		
		SessionFuncionario sessionFuncionario = new SessionFuncionario();
		SessionCliente sessionCliente = new SessionCliente();
		SessionVeiculo sessionVeiculo = new SessionVeiculo();
		

		for (int i = 0; i < listnota.length; i++) {
			NotaVenda nota = (NotaVenda) listnota[i];
			nota.setFuncionario( (Funcionario) sessionFuncionario.findByPrimaryKey(nota.getFuncionario()) );
			nota.setCliente((Cliente) sessionCliente.findByPrimaryKey( nota.getCliente().getId() ));
			nota.setVeiculo( (Veiculo) sessionVeiculo.findByPrimaryKey(nota.getVeiculo()));
		}
		
		return listnota;
	}
}
