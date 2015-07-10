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

import Dominio.Evento;
import Dominio.StatusEvento;
import Dominio.TiposEvento;
import Interfaces.IOrcamentoRepository;

public class OrcamentoRepository extends RepositoryBase<Evento> implements
		IOrcamentoRepository {

	public List<Evento> findAll() {
		try {
			String q = "select e from Evento e where e.status=:status order by e.nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("status", StatusEvento.ORCAMENTO);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Evento> findAll(String nome, Date inicioCad, Date fimCad,
			Date inicioReal, Date fimReal, TiposEvento tipoEvento) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Evento> criteriaQuery = criteriaBuilder
					.createQuery(Evento.class);
			Root<Evento> evento = criteriaQuery.from(Evento.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!nome.isEmpty()) {
				Path<String> n = evento.get("nome");
				Predicate where = criteriaBuilder.like(n, "%" + nome + "%");
				condicoes.add(where);
			}
			if (inicioCad != null && fimCad != null) {

				inicioCad.setHours(0);
				inicioCad.setMinutes(0);
				inicioCad.setSeconds(0);

				fimCad.setHours(23);
				fimCad.setMinutes(59);
				fimCad.setSeconds(0);

				Path<Date> date = evento.<Date> get("datacriacao");
				Predicate where = criteriaBuilder.between(date, inicioCad,fimCad);
				condicoes.add(where);

			}

			if (inicioReal != null && fimReal != null) {

				inicioReal.setHours(0);
				inicioReal.setMinutes(0);
				inicioReal.setSeconds(0);

				fimReal.setHours(23);
				fimReal.setMinutes(59);
				fimReal.setSeconds(0);

				Path<Date> date = evento.<Date> get("dataevento");
				Predicate where = criteriaBuilder.between(date, inicioReal,
						fimReal);
				condicoes.add(where);

			}

			if (tipoEvento != TiposEvento.TODOS) {
				Path<TiposEvento> tipo = evento.get("tipo");
				Predicate where = criteriaBuilder.equal(tipo, tipoEvento);
				condicoes.add(where);
			}

			Path<StatusEvento> status = evento.get("status");
			Predicate where = criteriaBuilder.equal(status,
					StatusEvento.ORCAMENTO);
			condicoes.add(where);

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(evento.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
