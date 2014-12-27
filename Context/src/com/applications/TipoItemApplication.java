package com.applications;

import java.util.List;

import com.domain.TipoItem;
import com.implementations.RepositoryIplement;

public class TipoItemApplication extends RepositoryIplement<TipoItem> {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoItem> FindAll() {
		try {
			return entityManager.createQuery(
					"FROM " + TipoItem.class.getName() + " ORDER BY nome ASC")
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<TipoItem> getTipoItens(boolean ativo) {

		try {
			return entityManager.createQuery(
					"FROM " + TipoItem.class.getName() + " WHERE ativo = "
							+ ativo + " ORDER BY nome ASC").getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
