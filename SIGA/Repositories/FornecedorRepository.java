package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Fornecedor;
import Interfaces.IFornecedorRepository;
import Interfaces.IPersistenceManager;

public class FornecedorRepository extends RepositoryBase<Fornecedor> implements
		IFornecedorRepository {

	public FornecedorRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAll(String coluna, String valor) {
		String q = "from Fornecedor where " + coluna + " like :valor";
		Query query = entityManager.createQuery(q);
		query.setParameter("valor", "%" + valor + "%");
		return query.getResultList();
	}

}
