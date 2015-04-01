package Repositories;

import Dominio.FormaPagamento;
import Interfaces.IFormaPagamento;
import Interfaces.IPersistenceManager;

import java.util.*;

import javax.persistence.Query;

public class FormaPagamentoRepository extends RepositoryBase<FormaPagamento>
implements IFormaPagamento{

	
	public FormaPagamentoRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);
	}
	@SuppressWarnings("unchecked")
	public List<FormaPagamento> allFormaPagamento(String coluna,String valor){

		String q = "from formapagamento order by forma";
		Query query = entityManager.createQuery(q);
		
		return query.getResultList();	
		
	}
	
}
