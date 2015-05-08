package com.interfaces;

import java.util.List;

import com.dominio.Venda;
import com.dominio.VendaDia;
import com.dominio.VendaMes;

public interface IVendaContext extends IBaseContext<Venda> {

	public List<VendaMes> buscarVendasMes();
	
	public List<VendaDia> buscarVendasDia();
}
