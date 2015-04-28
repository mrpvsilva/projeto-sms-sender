package com.controllers;

import com.dominio.Produto;
import com.dominio.ProdutoVendido;
import com.dominio.Venda;
import com.repositorios.StockEntities;

public class VendaController {
	private StockEntities db;

	public VendaController() {
		db = new StockEntities();
	}

	public void cadastrar(Venda venda) {
		try {
			db.Vendas.add(venda);
			for (ProdutoVendido pv : venda.getProdutosvendidos()) {
				Produto p = pv.getProduto();
				int q = p.getQuantidade() - pv.getQuantidade();
				p.setQuantidade(q);
				db.Produtos.update(p);
			}
		} catch (Exception ex) {

		}
	}
}
