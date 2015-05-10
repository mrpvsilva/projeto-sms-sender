package com.contexts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.dominio.Venda;
import com.dominio.VendaDia;
import com.dominio.VendaMes;
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

	@Override
	public List<VendaDia> buscarVendasDia() {
		open();
		Calendar cal = Calendar.getInstance();

		String q = "SELECT Date(dataHoraVenda) as data,sum(valorTotal) as valor FROM vendas  group by Date(dataHoraVenda)";
		Query query = manager.createNativeQuery(q);
		List<Object[]> lista = query.getResultList();

		List<VendaDia> l = new ArrayList<VendaDia>();

		for (Object[] row : lista) {
			VendaDia vm = new VendaDia();
			vm.setData((Date) row[0]);
			vm.setValor((BigDecimal) row[1]);
			l.add(vm);
		}
		return l;
	}

	public List<VendaMes> buscarVendasMes() {

		open();
		Calendar cal = Calendar.getInstance();
		String q = String
				.format("SELECT MONTH(dataHoraVenda) as mes,sum(valorTotal) as valor FROM vendas  group by MONTH(dataHoraVenda)",
						cal.get(Calendar.YEAR));
		Query query = manager.createNativeQuery(q);

		List<Object[]> lista = query.getResultList();

		List<VendaMes> l = new ArrayList<VendaMes>();

		for (Object[] row : lista) {
			VendaMes vm = new VendaMes();
			vm.setMes((Integer) row[0]);
			vm.setValor((BigDecimal) row[1]);
			l.add(vm);
		}

		return l;

	}

	public static void main(String[] args) {

		for (VendaDia vm : new VendaContext().buscarVendasDia()) {
			System.out.println(vm.getData() + "=" + vm.getValor());
		}
	}

}
