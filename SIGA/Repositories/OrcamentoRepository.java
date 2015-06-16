package Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import Dominio.Evento;
import Dominio.TiposEvento;
import Interfaces.IOrcamentoRepository;

public class OrcamentoRepository extends RepositoryBase<Evento> implements
		IOrcamentoRepository {

	public List<Evento> findAll() {
		try {
			open();
			String q = "from Evento where status='ORCAMENTO' order by nome";
			return entityManager.createQuery(q).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//entityManager.close();
		}
	}

	@Override
	public List<Evento> findAll(String nome, Date inicio, Date fim,
			TiposEvento tipoEvento) {
		try {

			open();

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
			if (inicio != null && fim != null) {

				inicio.setHours(0);
				inicio.setMinutes(0);
				inicio.setSeconds(0);

				fim.setHours(0);
				fim.setMinutes(0);
				fim.setSeconds(0);

				Path<Date> date = evento.<Date> get("datacriacao");
				Predicate where = criteriaBuilder.between(date, inicio, fim);
				condicoes.add(where);

			}

			if (tipoEvento != TiposEvento.TODOS) {
				Path<String> tipo = evento.get("tipo");
				Predicate where = criteriaBuilder.equal(tipo,
						tipoEvento.toString());
				condicoes.add(where);
			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(evento.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//entityManager.close();
		}
	}

}
