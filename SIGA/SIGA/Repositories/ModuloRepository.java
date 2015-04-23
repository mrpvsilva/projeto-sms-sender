package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Modulo;
import Interfaces.IModuloRepository;
import PersistenceManagerFactory.Factory;

public class ModuloRepository extends RepositoryBase<Modulo> implements
		IModuloRepository {

	public List<Modulo> findAll() {
		try {
			open();
			String q = "from Modulo order by nome";
			Query query = entityManager.createQuery(q);
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Modulo> findAll(String nome) {
		try {
			open();
			String q = "from Modulo where nome like :nome  order by nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", "%" + nome + "%");
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}
}
