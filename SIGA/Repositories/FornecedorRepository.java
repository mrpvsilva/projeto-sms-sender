package Repositories;

import java.util.List;
import javax.persistence.Query;
import Dominio.Fornecedor;
import Interfaces.IFornecedorRepository;

public class FornecedorRepository extends RepositoryBase<Fornecedor> implements
		IFornecedorRepository {

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAll(String coluna, String valor) {
		open();
		String q = "from Fornecedor where " + coluna + " like :valor";
		Query query = entityManager.createQuery(q);
		query.setParameter("valor", "%" + valor + "%");
		List<Fornecedor> l = query.getResultList();
		return l;
	}

}
