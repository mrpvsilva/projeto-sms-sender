package Repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Dominio.Lembrete;
import Dominio.Usuario;
import Interfaces.ILembreteRepository;

public class LembreteRepository extends RepositoryBase<Lembrete> implements
		ILembreteRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll() {
		try {

			String q = "select l from Lembrete l order by l.datahora desc";
			Query query = entityManager.createQuery(q);
			List<Lembrete> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findAll(Date dataInicial, Date dataFinal,
			String assunto, Usuario destinatario) {
		try {

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
		}
	}

	@Override
	public List<Lembrete> findAll(Usuario destinatario) {
		try {
			String q = "select l from Lembrete l where l.destinatario = :destinatario order by l.datahora desc";
			Query query = entityManager.createQuery(q);
			query.setParameter("destinatario", destinatario);
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Lembrete> notificarLembretes(Usuario destinatario) {
		try {

			Calendar fim = Calendar.getInstance();
			fim.add(Calendar.MINUTE, 30);

			System.out.println(fim.getTime().toString());

			String q = "select l from Lembrete l where l.destinatario = :destinatario and l.lido = false and l.datahora <= :fim ";
			return entityManager.createQuery(q)
					.setParameter("destinatario", destinatario)					
					.setParameter("fim", fim.getTime()).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public void limparLembretesNotificacao(Usuario destinatario) {
		try {
			entityManager.getTransaction().begin();			

			Calendar fim = Calendar.getInstance();
			fim.add(Calendar.MINUTE, 30);

			Calendar datahora = Calendar.getInstance();		

			String q = "update Lembrete l set l.lido = true, l.datahoraleitura = :datahora where l.destinatario = :destinatario and l.lido = false and l.datahora  <= :fim ";

			entityManager.createQuery(q)
					.setParameter("datahora", datahora.getTime())
					.setParameter("destinatario", destinatario)					
					.setParameter("fim", fim.getTime()).executeUpdate();

			entityManager.getTransaction().commit();

		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
		}

	}

}
