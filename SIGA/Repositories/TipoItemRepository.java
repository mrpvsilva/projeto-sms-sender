package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoItem;
import Interfaces.IPersistenceManager;
import Interfaces.ITipoItemRepository;

public class TipoItemRepository extends RepositoryBase<TipoItem> implements
		ITipoItemRepository {

	public TipoItemRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll() {

		String q = "from TipoItem order by nome";
		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll(String nome, String ativo) {
		String q = "from TipoItem where ";

		if (!nome.equals("")) {
			q += " nome like '%" + nome + "%' and";
		}

		if (ativo.equals("Todos")) {
			q += " ativo in (0,1)";
		} else if (ativo.equals("Ativo")) {
			q += " ativo = 1";
		} else {
			q += " ativo = 0";
		}

		q += " order by nome";

		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}

	@Override
	public TipoItem find(String nome) {
		String q = "from TipoItem where nome = :nome";
		Query query = entityManager.createQuery(q);
		query.setParameter("nome", nome);
		return (TipoItem) query.getSingleResult();
	}

}
