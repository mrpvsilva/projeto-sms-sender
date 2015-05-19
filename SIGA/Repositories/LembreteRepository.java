package Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import Dominio.Lembrete;
import Dominio.Usuario;
import Interfaces.ILembreteRepository;
import PersistenceManagerFactory.Factory;

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
			String destinatario) {
		try {
			open();

			CriteriaBuilder criteiraBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Lembrete> criteriaQuery = criteiraBuilder
					.createQuery(Lembrete.class);
			Root<Lembrete> lembrete = criteriaQuery.from(Lembrete.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (destinatario != null) {
				Join<Lembrete, Usuario> usuario = lembrete.join("destinatario",
						JoinType.INNER);
				Predicate where = criteiraBuilder.equal(usuario.get("usuario"),
						destinatario);
				condicoes.add(where);
			}
			if (dataInicial != null && dataFinal != null) {
				
				 dataInicial.setHours(0);
				 dataInicial.setMinutes(0);
				 dataInicial.setSeconds(0);
				
				 dataFinal.setHours(23);
				 dataFinal.setMinutes(59);
				 dataFinal.setSeconds(59);
				
				Path<Date> date = lembrete.<Date>get("datahora");
				Predicate where = criteiraBuilder.between(date, dataInicial,
						dataFinal);
				condicoes.add(where);

			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteiraBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteiraBuilder.desc(lembrete.get("datahora")));			
			 
			
			

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}
}
