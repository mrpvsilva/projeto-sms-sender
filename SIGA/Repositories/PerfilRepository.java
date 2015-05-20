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
			open();
			String q = "from Perfil where nome = :nome ";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", nome);
			Perfil p = (Perfil) query.getSingleResult();

			return p;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Perfil> findAll() {
		try {
			open();
			String q = "from Perfil where nome <> 'root' order by nome";
			Query query = entityManager.createQuery(q);
			List<Perfil> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Perfil> findAllWithRoot() {
		try {
			String q = "from Perfil";
			Query query = entityManager.createQuery(q);
			List<Perfil> l = query.getResultList();

			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

}
