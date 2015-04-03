package Repositories;

import java.util.List;
import javax.persistence.Query;
import Dominio.Lembrete;
import Interfaces.ILembreteRepository;

public class LembreteRepository extends RepositoryBase<Lembrete> implements
		ILembreteRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll() {
		open();
		String q = "from Lembrete order by datahora desc";
		Query query = entityManager.createQuery(q);
		List<Lembrete> l = query.getResultList();
		close();
		return l;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll(String coluna, String valor) {
		open();
		String q = "from Lembrete where " + coluna
				+ " like :valor order by datahora desc";

		Query query = entityManager.createQuery(q);
		query.setParameter("valor", "%" + valor + "%");
		List<Lembrete> l = query.getResultList();
		close();
		return l;
	}
}
