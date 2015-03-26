package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Item;
import Interfaces.IItemRepository;
import Interfaces.IPersistenceManager;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	public ItemRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}

	@Override
	public List<Item> findAll(String campo, String txt) {
		String q = "from Item where " + campo + " like :txt";
		Query query = entityManager.createQuery(q);
		query.setParameter("txt", "%" + txt + "%");
		return query.getResultList();
	}

}
