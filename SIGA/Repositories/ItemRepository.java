package Repositories;

import java.util.List;

import javax.persistence.Query;

import persistenceManagerFactory.Factory;
import Dominio.Item;
import Interfaces.IItemRepository;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		open();
		String q = "from Item order by nome";
		Query query = entityManager.createQuery(q);
		List<Item> l = query.getResultList();
		Factory.renewFactory();
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll(String campo, String txt) {
		open();
		String q = "from Item";
		if (!txt.equals(""))
			q += " where " + campo + " like '%" + txt + "%'";

		q += " order by nome";
		Query query = entityManager.createQuery(q);
		List<Item> l = query.getResultList();
		Factory.renewFactory();
		return l;
	}

}
