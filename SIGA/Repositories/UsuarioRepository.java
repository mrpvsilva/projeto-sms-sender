package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Usuario;
import Interfaces.IPersistenceManager;
import Interfaces.IUsuarioRepository;

public class UsuarioRepository extends RepositoryBase<Usuario> implements
		IUsuarioRepository {

	public UsuarioRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);
	}

	@Override
	public Usuario getUsuario(String usuario, String senha) {

		try {

			String query = "from Usuario where usuario = :usuario and senha=:senha ";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			q.setParameter("senha", senha);
			Usuario u = (Usuario) q.getSingleResult();
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Usuario> findAll(String campo, String value) {
		String q = "from Usuario where " + campo + " like :valor";
		Query query = entityManager.createQuery(q);
		query.setParameter("valor", "%" + value + "%");

		return query.getResultList();

	}

	@Override
	public Usuario getUsuario(String usuario) {
		try {

			String query = "from Usuario where usuario = :usuario";
			Query q = entityManager.createQuery(query);
			q.setParameter("usuario", usuario);
			Usuario u = (Usuario) q.getSingleResult();
			return u;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
