package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Permissao;
import Interfaces.IPermissaoRepository;

public class PermissaoRepository extends RepositoryBase<Permissao> implements
		IPermissaoRepository {

	@Override
	public Permissao find(String nomeModulo, long idPerfil) {
		try {
			open();
			String q = "select p from Permissao as p where p.modulo.id = ( select m.id from Modulo as m where nome = :nomemodulo) and p.perfil.id = :idperfil ";
			Query query = entityManager.createQuery(q);
			query.setParameter("nomemodulo", nomeModulo);
			query.setParameter("idperfil", idPerfil);
			Permissao p = (Permissao) query.getSingleResult();

			return p;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Permissao> findAll(long idPerfil) {

		try {
			open();
			String q = "from Permissao as p where p.perfil.id = :idperfil ";
			Query query = entityManager.createQuery(q);
			query.setParameter("idperfil", idPerfil);
			return query.getResultList();

		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}
	}

}
