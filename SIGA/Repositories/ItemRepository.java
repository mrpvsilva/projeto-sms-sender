package Repositories;

import java.util.List;

import javax.persistence.Query;
import Dominio.Item;
import Interfaces.IItemRepository;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		try {			
			String q = "select i from Item i order by i.nome";
			Query query = entityManager.createQuery(q);
			List<Item> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll(String campo, String txt) {
		try {
			
			String q = "select i from Item i";
			if (!txt.equals(""))
				q += " where i." + campo + " like '%" + txt + "%'";

			q += " order by i.nome";
			Query query = entityManager.createQuery(q);
			List<Item> l = query.getResultList();
			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@Override
	public List<Item> findByTipo(String tipoItem) {
		try {
			
			String q = "select i from Item i where i.tipoItem.Nome =:tipoItem";
			Query query = entityManager.createQuery(q);
			query.setParameter("tipoItem", tipoItem);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}
}
