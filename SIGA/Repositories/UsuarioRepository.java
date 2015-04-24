package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Usuario;
import Interfaces.IUsuarioRepository;
import PersistenceManagerFactory.Factory;

public class UsuarioRepository extends RepositoryBase<Usuario> implements
		IUsuarioRepository {

	@Override
	public List<Usuario> findAll() {
		try {
			open();
			String q = "from Usuario order by usuario";
			Query query = entityManager.createQuery(q);
			List<Usuario> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll(String campo, String value) {
		try {
			open();
			String q = "from Usuario where " + campo + " like :valor";
			Query query = entityManager.createQuery(q);
			query.setParameter("valor", "%" + value + "%");
			List<Usuario> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public Usuario getUsuario(String usuario, String senha) {

		try {
			open();
			String query = "from Usuario where usuario = :usuario and senha=:senha ";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			q.setParameter("senha", senha);
			Usuario u = (Usuario) q.getSingleResult();

			return u;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public Usuario getUsuario(String usuario) {
		try {
			open();
			String query = "from Usuario where usuario = :usuario";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			Usuario u = (Usuario) q.getSingleResult();

			return u;

		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}
}
