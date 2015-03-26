package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoServico;
import Interfaces.IPersistenceManager;
import Interfaces.ITipoServicoRepository;

public class TipoServicoRepository extends RepositoryBase<TipoServico>
		implements ITipoServicoRepository {

	public TipoServicoRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}

	@Override
	public List<TipoServico> FindAll() {
		Query q = entityManager.createQuery("from TipoServico order by nome",
				TipoServico.class);
		return q.getResultList();
	}

	public List<TipoServico> FindAll(boolean ativo) {
		Query q = entityManager
				.createQuery("from TipoServico where ativo=:ativo order by nome");
		q.setParameter("ativo", ativo);
		return q.getResultList();
	}

	public TipoServico getTipoByName(String nome) {
		Query q = entityManager
				.createQuery("from TipoServico where nome=:nome");
		q.setParameter("nome", nome);
		return (TipoServico) q.getSingleResult();
	}

}
