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
			
			String q = "select m from Modulo m order by m.nome";
			Query query = entityManager.createQuery(q);
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@Override
	public List<Modulo> findAll(String nome) {
		try {			
			String q = "select from Modulo m where m.nome like :nome  order by m.nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", "%" + nome + "%");
			List<Modulo> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}
}
