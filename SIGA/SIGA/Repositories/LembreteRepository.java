package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Lembrete;
import Interfaces.ILembreteRepository;
import PersistenceManagerFactory.Factory;

public class LembreteRepository extends RepositoryBase<Lembrete> implements
		ILembreteRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll() {
		try {
			open();
			String q = "from Lembrete order by datahora desc";
			Query query = entityManager.createQuery(q);
			List<Lembrete> l = query.getResultList();
			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll(String coluna, String valor) {
		try {
			open();
			String q = "from Lembrete where " + coluna
					+ " like :valor order by datahora desc";

			Query query = entityManager.createQuery(q);
			query.setParameter("valor", "%" + valor + "%");
			List<Lembrete> l = query.getResultList();

			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}
}
