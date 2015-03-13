package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoServico;
import Interfaces.IPersistenceManager;
import Interfaces.ITipoServicoRepository;

public class TipoServicoRepository extends RepositoryBase<TipoServico>
		implements ITipoServicoRepository<TipoServico> {

	public TipoServicoRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}

	@Override
	public List<TipoServico> FindAll() {
		Query q = entityManager.createQuery("from TipoServico order by nome");
		System.out.println("aqui");
		return q.getResultList();
	}

	public TipoServico getTipoByName(String nome) {
		Query q = entityManager
				.createQuery("from TipoServico where nome=:nome");
		q.setParameter("nome", nome);
		System.out.println("aqui");
		return (TipoServico) q.getSingleResult();
	}

}
