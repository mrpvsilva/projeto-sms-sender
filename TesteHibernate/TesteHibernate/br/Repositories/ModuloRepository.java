package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Modulo;
import Interfaces.IModuloRepository;
import Util.Factory;

public class ModuloRepository extends RepositoryBase<Modulo> implements
		IModuloRepository {

	public List<Modulo> findAll() {
		try {
			entityManager = Factory.createEntityManager();
			String q = "from Modulo order by nome";
			Query query = entityManager.createQuery(q);
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<Modulo> findAll(String nome) {
		try {
			//entityManager.getDelegate
			String q = "from Modulo where nome like :nome  order by nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", "%" + nome + "%");
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		}
	}
}
