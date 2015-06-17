package Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Dominio.Fornecedor;
import Dominio.Servico;
import Interfaces.IFornecedorRepository;

public class FornecedorRepository extends RepositoryBase<Fornecedor> implements
		IFornecedorRepository {

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAll(String campo, String valor,
			String servico) {

		try {	
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Fornecedor> criteriaQuery = criteriaBuilder
					.createQuery(Fornecedor.class);
			Root<Fornecedor> fornecedor = criteriaQuery.from(Fornecedor.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!valor.isEmpty()) {

				Path<String> n = fornecedor.get(campo);
				Predicate where = criteriaBuilder.like(n, "%" + valor + "%");
				condicoes.add(where);

			}

			if (!servico.equals("TODOS")) {
				Path<Servico> tipo = fornecedor.get("servico");
				Predicate where = criteriaBuilder.equal(tipo.get("nome"),
						servico);
				condicoes.add(where);
			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(fornecedor.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

	}
}
