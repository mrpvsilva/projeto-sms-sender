package com.contexts;

import java.util.List;

import javax.persistence.Query;

import com.dominio.Venda;
import com.interfaces.IVendaContext;

public class VendaContext extends BaseContext<Venda> implements IVendaContext {

	public List<Venda> findAll() {

		try {
			open();
			return manager
					.createQuery("from Venda order by dataHoraVenda desc")
					.getResultList();

		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

}
