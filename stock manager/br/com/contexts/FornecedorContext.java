package com.contexts;

import java.util.List;

import javax.persistence.Query;
import com.dominio.Fornecedor;
import com.interfaces.IFornecedorContext;

public class FornecedorContext extends BaseContext<Fornecedor> implements
		IFornecedorContext {

	@Override
	public List<Fornecedor> findAll() {
		try {
			open();
			String q = "from Fornecedor order by nome";
			Query query = manager.createQuery(q);
			return query.getResultList();
		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

	@Override
	public List<Fornecedor> findAll(String nome) {

		try {
			open();
			String q = "from Fornecedor where nome like :nome order by nome";
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
