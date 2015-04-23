package Repositories;

import java.util.List;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import javax.persistence.Query;

import Dominio.Item;
import Interfaces.IItemRepository;
import PersistenceManagerFactory.Factory;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		try {
			open();
			String q = "from Item order by nome";
			Query query = entityManager.createQuery(q);
			List<Item> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll(String campo, String txt) {
		try {
			open();
			String q = "from Item";
			if (!txt.equals(""))
				q += " where " + campo + " like '%" + txt + "%'";

			q += " order by nome";
			Query query = entityManager.createQuery(q);
			List<Item> l = query.getResultList();
			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}
}
