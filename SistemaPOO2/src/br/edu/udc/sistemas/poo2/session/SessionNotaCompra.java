package br.edu.udc.sistemas.poo2.session;

import br.edu.udc.sistemas.poo2.dao.DAOFornecedor;
import br.edu.udc.sistemas.poo2.dao.DAOFuncionario;
import br.edu.udc.sistemas.poo2.dao.DAONotaCompra;
import br.edu.udc.sistemas.poo2.entity.Fornecedor;
import br.edu.udc.sistemas.poo2.entity.Funcionario;
import br.edu.udc.sistemas.poo2.entity.NotaCompra;

public class SessionNotaCompra extends SessionNota {

	
	public SessionNotaCompra() {
		this.dao = new DAONotaCompra();
	}
	
	@Override
	public Object[] find(Object obj) throws Exception {
		Object listnota[] = this.dao.find(obj);
		
		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOFornecedor daoFornecedor = new DAOFornecedor();
		

		for (int i = 0; i < listnota.length; i++) {
			NotaCompra nota = (NotaCompra) listnota[i];
			nota.setFuncionario( (Funcionario) daoFuncionario.findByPrimaryKey(nota.getFuncionario()) );
			nota.setFornecedor((Fornecedor) daoFornecedor.findByPrimaryKey(nota.getFornecedor() ));
		}
		
		return listnota;
	}
}
