package Repositories;

import java.util.List;

import javax.persistence.Query;

import Dominio.TipoItem;
import Interfaces.ITipoItemRepository;
import Util.Factory;

public class TipoItemRepository extends RepositoryBase<TipoItem> implements
		ITipoItemRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll() {
		try {
			
			String q = "select t from TipoItem t order by t.nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
			
		} catch (Exception ex) {
			return null;
		} 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> findAll(String nome, String ativo) {
		try {
			
			String q = "select t from TipoItem t where ";

			if (!nome.equals("")) {
				q += " t.nome like '%" + nome + "%' and";
			}

			if (ativo.equals("Todos")) {
				q += " t.ativo in (0,1)";
			} else if (ativo.equals("Ativo")) {
				q += " ativo = 1";
			} else {
				q += " ativo = 0";
			}

			q += " order by t.nome";

			Query query = entityManager.createQuery(q);
			List<TipoItem> l = query.getResultList();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@Override
	public TipoItem find(String nome) {
		try {			
			String q = "from TipoItem where nome = :nome";
			Query query = entityManager.createQuery(q);
			query.setParameter("nome", nome);
			TipoItem t = (TipoItem) query.getSingleResult();
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	@Override
	public List<String> DDLTipoItens() {
		try {			
			String q = "select t.nome from TipoItem t order by nome";
			Query query = entityManager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}

}
