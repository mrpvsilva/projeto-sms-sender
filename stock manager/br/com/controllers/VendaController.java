package com.controllers;

import java.util.List;

import com.dominio.Produto;
import com.dominio.ProdutoVendido;
import com.dominio.Venda;
import com.repositorios.StockEntities;

public class VendaController {
	private StockEntities db;

	public VendaController() {
		db = new StockEntities();
	}

	public List<Venda> listarTodos() {
		return db.Vendas.findAll();
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

	public List<Produto> pesquisarProduto(String pesquisa) {
		return db.Produtos.findAll(pesquisa);
	}
}
