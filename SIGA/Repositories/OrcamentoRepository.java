package Repositories;

import java.util.List;

import org.hibernate.HibernateException;

import Dominio.Evento;
import Interfaces.IOrcamentoRepository;

public class OrcamentoRepository extends RepositoryBase<Evento> implements
		IOrcamentoRepository {

	public List<Evento> findAll() {
		try {
			open();
			String q = "from Evento where status='ORCAMENTO' order by nome";
			return entityManager.createQuery(q).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

}
