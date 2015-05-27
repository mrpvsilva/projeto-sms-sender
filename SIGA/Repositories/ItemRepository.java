package Repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import Dominio.Item;
import Interfaces.IItemRepository;

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

	@Override
	public List<Item> findByTipo(String tipoItem) {
		try {
			open();
			String q = "from Item i where i.tipoItem.Nome =:tipoItem";
			Query query = entityManager.createQuery(q);
			query.setParameter("tipoItem", tipoItem);
			return query.getResultList();
		} catch (HibernateException ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}
}
