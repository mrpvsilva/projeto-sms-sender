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

import Dominio.Evento;
import Dominio.Fornecedor;
import Dominio.Telefone;
import Dominio.TipoServico;
import Dominio.TiposEvento;
import Interfaces.IFornecedorRepository;
import Util.Factory;

public class FornecedorRepository extends RepositoryBase<Fornecedor> implements
		IFornecedorRepository {

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAll(String campo, String valor,
			String tipoServico) {

		try {

			open();

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

			if (!tipoServico.equals("SELECIONE")) {
				Path<TipoServico> tipo = fornecedor.get("tipoServico");
				Predicate where = criteriaBuilder.equal(tipo.get("nome"),
						tipoServico);
				condicoes.add(where);
			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(fornecedor.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}
}
