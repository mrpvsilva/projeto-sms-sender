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
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoServico> findAll() {
		Query q = entityManager.createQuery("from TipoServico order by nome",
				TipoServico.class);
		return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<TipoServico> findAll(boolean ativo) {
		Query q = entityManager
				.createQuery("from TipoServico where ativo=:ativo order by nome");
		q.setParameter("ativo", ativo);
		return q.getResultList();
	}

	public TipoServico findByName(String nome) {
		Query q = entityManager
				.createQuery("from TipoServico where nome=:nome");
		q.setParameter("nome", nome);
		return (TipoServico) q.getSingleResult();
	}

	@Override
	public List<TipoServico> findAll(String nome, String ativo) {
		String q = "from TipoServico where ";

		if (!nome.equals("")) {
			q += " nome like '%" + nome + "%' and";
		}

		if (ativo.equals("Todos")) {
			q += " ativo in (0,1)";
		} else if (ativo.equals("Ativo")) {
			q += " ativo = 1";
		} else {
			q += " ativo = 0";
		}

		q += " order by nome";

		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}

}
