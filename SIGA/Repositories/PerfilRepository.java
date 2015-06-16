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
			String q = "select p from Perfil p where p.nome = :nome ";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", nome);
			Perfil p = (Perfil) query.getSingleResult();

			return p;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}

	@Override
	public List<Perfil> findAll() {
		try {
			open();
			String q = "select p from Perfil p where p.nome <> 'root' order by p.nome";
			Query query = entityManager.createQuery(q);
			List<Perfil> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}

	@Override
	public List<Perfil> findAllWithRoot() {
		try {
			String q = "select p from Perfil p";
			Query query = entityManager.createQuery(q);
			List<Perfil> l = query.getResultList();

			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}

	}

}
