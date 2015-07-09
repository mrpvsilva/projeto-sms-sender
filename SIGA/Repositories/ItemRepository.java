package Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Dominio.Item;
import Dominio.Servico;
import Dominio.TipoCobranca;
import Interfaces.IItemRepository;
import Util.TipoAtivo;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		try {
			String q = "select i from Item i order by i.nome";
			Query query = entityManager.createQuery(q);
			List<Item> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll(String item, TipoCobranca tipo, TipoAtivo ativo) {
		try {

			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Item> criteriaQuery = criteriaBuilder
					.createQuery(Item.class);
			Root<Item> _item = criteriaQuery.from(Item.class);
			List<Predicate> condicoes = new ArrayList<Predicate>();

			if (!item.isEmpty()) {
				Path<String> n = _item.get("nome");
				Predicate where = criteriaBuilder.like(n, "%" + item + "%");
				condicoes.add(where);
			}
			if (tipo != TipoCobranca.TODOS) {
				Path<TipoCobranca> _tipo = _item
						.<TipoCobranca> get("tipocobranca");
				Predicate where = criteriaBuilder.equal(_tipo, tipo);
				condicoes.add(where);

			}

			if (ativo != TipoAtivo.Todos) {
				Path<Boolean> _ativo = _item.<Boolean> get("ativo");
				Predicate where = criteriaBuilder.equal(_ativo,
						(ativo == TipoAtivo.Ativo));
				condicoes.add(where);

			}

			Predicate[] condicoesComoArray = condicoes
					.toArray(new Predicate[condicoes.size()]);
			Predicate todasCondicoes = criteriaBuilder.and(condicoesComoArray);
			criteriaQuery.where(todasCondicoes);
			criteriaQuery.orderBy(criteriaBuilder.asc(_item.get("nome")));

			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Item> findByTipo(String tipoItem) {
		try {

			String q = "select i from Item i where i.ativo=true and i.tipoItem = (select t from TipoItem t where t.nome=:nome)";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", tipoItem);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Item> findAll(boolean ativo) {
		try {
			String q = "select i from Item i where i.ativo=:ativo";
			Query query = entityManager.createQuery(q);
			query.setParameter("ativo", ativo);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		new ItemRepository().findByTipo("Bebidas");
	}
}
