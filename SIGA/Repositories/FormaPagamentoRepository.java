package Repositories;

import Dominio.FormaPagamento;
import Interfaces.IFormaPagamento;
import java.util.List;

public class FormaPagamentoRepository extends RepositoryBase<FormaPagamento>
		implements IFormaPagamento {

	@SuppressWarnings("unchecked")
	public List<FormaPagamento> allFormaPagamento(String coluna, String valor) {
		// open();
		// String q = "from formapagamento order by forma";
		// Query query = entityManager.createQuery(q);
		//
		// List<FormaPagamento> l = query.getResultList();
		//clear();
		// return l;

		return null;

	}

}
