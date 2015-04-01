package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoPagamento;
import Interfaces.IPersistenceManager;
import Interfaces.ITipoPagamentoRepository;

public class TipoPagamentoRepository extends RepositoryBase<TipoPagamento>
implements ITipoPagamentoRepository{

	public TipoPagamentoRepository(IPersistenceManager persistenceManager){
		super(persistenceManager);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPagamento> allTpPagamentos(String coluna, String valor) {
		String q = "from TipoPagamento order by descricao";
		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}
	
}
