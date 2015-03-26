package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.Lembrete;
import Interfaces.ILembreteRepository;
import Interfaces.IPersistenceManager;

public class LembreteRepository extends RepositoryBase<Lembrete> implements
		ILembreteRepository {

	public LembreteRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Lembrete> findAll() {

		String q = "from Lembrete order by datahora desc";
		Query query = entityManager.createQuery(q);
		return query.getResultList();

	}

	@Override
	public List<Lembrete> findAll(String coluna, String valor) {
		String q = "from Lembrete where " + coluna
				+ " like :valor order by datahora desc";

		Query query = entityManager.createQuery(q);
		query.setParameter("valor", "%" + valor + "%");
		return query.getResultList();
	}
}
