package Repositories;

import java.util.List;

import javax.persistence.Query;

import persistenceManagerFactory.Factory;
import Dominio.Perfil;
import Interfaces.IPerfilRepository;

public class PerfilRepository extends RepositoryBase<Perfil> implements
		IPerfilRepository {

	@Override
	public Perfil getPerfil(String nome) {
		open();
		String q = "from Perfil where nome = :nome ";
		Query query = entityManager.createQuery(q);
		query.setParameter("nome", nome);
		Perfil p = (Perfil) query.getSingleResult();

		return p;
	}

	@Override
	public List<Perfil> findAll() {
		open();
		String q = "from Perfil where nome <> 'root' order by nome";
		Query query = entityManager.createQuery(q);
		List<Perfil> l = query.getResultList();
		entityManager.close();
		Factory.renewFactory();
		return l;
	}

}
