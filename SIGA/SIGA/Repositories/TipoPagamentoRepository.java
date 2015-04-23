package Repositories;

import java.util.List;
import Dominio.TipoPagamento;
import Interfaces.ITipoPagamentoRepository;

public class TipoPagamentoRepository extends RepositoryBase<TipoPagamento>
		implements ITipoPagamentoRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPagamento> allTpPagamentos(String coluna, String valor) {
		// open();
		// String q = "from TipoPagamento order by descricao";
		// Query query = entityManager.createQuery(q);
		// List<TipoPagamento> l = query.getResultList();
		// clear();
		// return l;

		return null;
	}

}
