package Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import Dominio.Lembrete;
import Dominio.Usuario;
import Interfaces.ILembreteRepository;

public class LembreteRepository extends RepositoryBase<Lembrete> implements
		ILembreteRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll() {
		try {
			open();
			String q = "from Lembrete order by datahora desc";
			Query query = entityManager.createQuery(q);
			List<Lembrete> l = query.getResultList();
			// Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll(Date dataInicial, Date dataFinal,
			String assunto, Usuario destinatario) {
		try {
			open();

			CriteriaBuilder criteiraBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Lembrete> criteriaQuery = criteiraBuilder
					.createQuery(Lembrete.class);
			Root<Lembrete> lembrete = criteriaQuery.from(Lembrete.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			{
				Path<Usuario> usuario = lembrete.get("destinatario");
				Predicate where = criteiraBuilder.equal(usuario, destinatario);
				condicoes.add(where);
			}

			if (!assunto.isEmpty()) {
				Path<String> a = lembrete.get("assunto");
				Predicate where = criteiraBuilder.like(a, "%" + assunto + "%");
				condicoes.add(where);
			}
			if (dataInicial != null && dataFinal != null) {

				dataInicial.setHours(0);
				dataInicial.setMinutes(0);
				dataInicial.setSeconds(0);

				dataFinal.setHours(23);
				dataFinal.setMinutes(59);
				dataFinal.setSeconds(59);

				Path<Date> date = lembrete.<Date> get("datahora");
				Predicate where = criteiraBuilder.between(date, dataInicial,
						dataFinal);
				condicoes.add(where);

			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteiraBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery
					.orderBy(criteiraBuilder.desc(lembrete.get("datahora")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Lembrete> findAll(Usuario destinatario) {
		try {
			String q = "from Lembrete l where l.destinatario = :destinatario order by datahora desc";
			open();
			Query query = entityManager.createQuery(q);
			query.setParameter("destinatario", destinatario);

			return query.getResultList();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}
}
