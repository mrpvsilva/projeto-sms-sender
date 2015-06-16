package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Perfil;
import Interfaces.IPerfilRepository;
import Util.Factory;

public class PerfilRepository extends RepositoryBase<Perfil> implements
		IPerfilRepository {

	@Override
	public Perfil getPerfil(String nome) {
		try {

			String q = "select p from Perfil p where p.nome = :nome ";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", nome);
			return (Perfil) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Perfil> findAll() {
		try {
			String q = "select p from Perfil p where p.nome <> 'root' order by p.nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Perfil> findAllWithRoot() {
		try {
			String q = "select p from Perfil p";
			Query query = entityManager.createQuery(q);
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 

	}

}
