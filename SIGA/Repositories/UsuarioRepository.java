package Repositories;

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
	public Usuario GetUsuario(String usuario, String senha) {

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
}
