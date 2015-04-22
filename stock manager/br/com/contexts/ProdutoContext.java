package com.contexts;

import java.util.List;

import javax.persistence.Query;

import com.dominio.Produto;
import com.interfaces.IProdutoContexto;

public class ProdutoContext extends BaseContext<Produto> implements
		IProdutoContexto {

	@Override
	public List<Produto> findAll() {
		try {
			open();
			String q = "from Produto order by nome";
			Query query = manager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

	@Override
	public List<Produto> findAll(String nome) {

		try {
			open();
			String q = "from Produto where nome like :nome order by nome";
			Query query = manager.createQuery(q);
			query.setParameter("nome", "%" + nome + "%");
			return query.getResultList();
		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

}
