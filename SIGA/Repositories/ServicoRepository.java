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
import Dominio.Servico;
import Dominio.TiposEvento;
import Interfaces.IServicoRepository;

public class ServicoRepository extends RepositoryBase<Servico> implements
		IServicoRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> findAll() {
		try {

			Query query = entityManager
					.createQuery("select t from Servico t order by t.nome");
			List<Servico> l = query.getResultList();

			return l;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Servico> findAll(boolean ativo) {

		try {

			Query q = entityManager
					.createQuery("select t from Servico t where t.ativo=:ativo order by t.nome");
			q.setParameter("ativo", ativo);
			List<Servico> l = q.getResultList();

			return l;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Servico findByName(String nome) {

		try {

			Query q = entityManager
					.createQuery("select t from Servico t where t.nome=:nome");
			q.setParameter("nome", nome);
			Servico ts = (Servico) q.getSingleResult();

			return ts;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Servico> findAll(String nome, String ativo) {

		try {

			// String q = "select t from Servico t where ";
			//
			// if (!nome.equals("")) {
			// q += " t.nome like '%" + nome + "%' and";
			// }
			//
			// if (ativo.equals("Todos")) {
			// q += " t.ativo in (true,false)";
			// } else if (ativo.equals("Ativo")) {
			// q += " t.ativo = true";
			// } else {
			// q += " t.ativo = false";
			// }
			//
			// q += " order by t.nome";
			//
			// Query query = entityManager.createQuery(q);
			// List<Servico> l = query.getResultList();
			//
			// return l;

			CriteriaBuilder criteriaBuilder = entityManager	.getCriteriaBuilder();			
			CriteriaQuery<Servico> criteriaQuery = criteriaBuilder.createQuery(Servico.class);
			Root<Servico> servico = criteriaQuery.from(Servico.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!nome.isEmpty()) {
				Path<String> n = servico.get("nome");
				Predicate where = criteriaBuilder.like(n, "%" + nome + "%");
				condicoes.add(where);
			}
			if (!ativo.isEmpty() && !ativo.equals("Todos")) {

				Path<Boolean> _ativo = servico.<Boolean> get("ativo");
				Predicate where;

				if (ativo.equals("Ativo"))
					where = criteriaBuilder.equal(_ativo, true);
				else
					where = criteriaBuilder.equal(_ativo, false);

				condicoes.add(where);

			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(servico.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<String> DDL() {
		try {

			String q = "select t.nome from Servico t where t.ativo = 1 order by t.nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
