package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoItem;
import Interfaces.ITipoItemRepository;
import Util.Factory;

public class TipoItemRepository extends RepositoryBase<TipoItem> implements
		ITipoItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll() {
		try {
			open();
			String q = "from TipoItem order by nome";
			Query query = entityManager.createQuery(q);
			List<TipoItem> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll(String nome, String ativo) {
		try {
			open();
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
			List<TipoItem> l = query.getResultList();			
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public TipoItem find(String nome) {
		try {
			open();
			String q = "from TipoItem where nome = :nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", nome);
			TipoItem t = (TipoItem) query.getSingleResult();
			return t;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

}
