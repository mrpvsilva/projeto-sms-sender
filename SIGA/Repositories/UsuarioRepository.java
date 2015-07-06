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

			String q = "select u from Usuario u where u.usuario <> 'root' order by u.usuario";
			Query query = entityManager.createQuery(q);
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll(String campo, String value) {
		try {

			String q = "select u from Usuario u where  u.usuario <> 'root' and u."
					+ campo + " like :valor";
			Query query = entityManager.createQuery(q);
			query.setParameter("valor", "%" + value + "%");
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Usuario getUsuario(String usuario, String senha) {

		try {

			String query = " select u from Usuario u where u.usuario = :usuario and u.senha=:senha ";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			q.setParameter("senha", senha);
			return (Usuario) q.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Usuario getUsuario(String usuario) {
		try {

			String query = "select u from Usuario u where u.usuario = :usuario";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			return  (Usuario) q.getSingleResult();

		

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
