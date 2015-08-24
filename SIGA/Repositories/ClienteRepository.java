package Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Dominio.Cliente;
import Interfaces.IClienteRepository;

public class ClienteRepository extends RepositoryBase<Cliente> implements
		IClienteRepository {

	@Override
	public List<Cliente> findAll() {

		try {
			String q = "select c from Cliente c WHERE c.orcamento = false order by c.nomeCompleto";
			@SuppressWarnings("unchecked")
			List<Cliente> l = entityManager.createQuery(q).getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Cliente> findAll(String valor, String campo) {
		try {

			String q = "select c from Cliente c where c." + campo + " like '%"
					+ valor + "%' and c.orcamento = false order by c.nomeCompleto";
			return entityManager.createQuery(q, Cliente.class).getResultList();

		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Cliente find(String cpfcnpj) {
		try {
			String q = "select c from Cliente c where c.cpfCnpj= :cpfcnpj";
			Query query = entityManager.createQuery(q);
			query.setParameter("cpfcnpj", cpfcnpj);
			return (Cliente) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public long countCliente(String valor, String campo) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);

			Root<Cliente> cliente = cq.from(Cliente.class);

			cq.select(cb.count(cliente));

			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!valor.isEmpty()) {
				Path<String> c = cliente.get(campo);
				condicoes.add(cb.like(c, "%" + valor + "%"));
			}

			Path<Boolean> c = cliente.get("orcamento");
			condicoes.add(cb.equal(c, false));

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = cb.and(condicoesComoArray);
			cq.where(todasCondicoes);

			return entityManager.createQuery(cq).getSingleResult();

		} catch (NoResultException ex) {
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Cliente> findAll(String valor, String campo, int page, int total) {
		try {

			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			
			CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
			
			Root<Cliente> cliente = criteriaQuery.from(Cliente.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!valor.isEmpty()) {
				Path<String> c = cliente.get(campo);
				condicoes.add(criteriaBuilder.like(c, "%" + valor + "%"));
			}
			Path<Boolean> c = cliente.get("orcamento");
			condicoes.add(criteriaBuilder.equal(c, false));

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(cliente
					.get("nomeCompleto")));

			return entityManager.createQuery(criteriaQuery)
					.setFirstResult((page - 1) * total).setMaxResults(total)
					.getResultList();

		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
