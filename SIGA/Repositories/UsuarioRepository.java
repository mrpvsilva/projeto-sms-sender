package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Usuario;
import Interfaces.IUsuarioRepository;
import Util.Factory;

public class UsuarioRepository extends RepositoryBase<Usuario> implements
		IUsuarioRepository {

	@Override
	public List<Usuario> findAll() {
		try {
			open();
			String q = "select u from Usuario u where u.usuario <> 'root' order by u.usuario";
			Query query = entityManager.createQuery(q);
			List<Usuario> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll(String campo, String value) {
		try {
			open();
			String q = "select u from Usuario where  u.usuario <> 'root' and u." + campo + " like :valor";
			Query query = entityManager.createQuery(q);
			query.setParameter("valor", "%" + value + "%");
			List<Usuario> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}

	}

	@Override
	public Usuario getUsuario(String usuario, String senha) {

		try {
			open();
			String query = " select u from Usuario u where u.usuario = :usuario and u.senha=:senha ";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			q.setParameter("senha", senha);
			Usuario u = (Usuario) q.getSingleResult();

			return u;
		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}

	}

	@Override
	public Usuario getUsuario(String usuario) {
		try {
			open();
			String query = "select u from Usuario u where u.usuario = :usuario";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			Usuario u = (Usuario) q.getSingleResult();

			return u;

		} catch (Exception ex) {
			return null;
		} finally {
			//entityManager.close();
		}
	}
}
