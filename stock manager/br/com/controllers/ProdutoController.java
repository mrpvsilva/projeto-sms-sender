package com.controllers;

import java.util.List;

import com.dominio.Produto;
import com.repositorios.StockEntities;

public class ProdutoController {

	private StockEntities db;

	public ProdutoController() {
		db = new StockEntities();
	}

	public List<Produto> listarTodos() {
		return db.Produtos.findAll();
	}

	public List<Produto> listarTodos(String nome) {
		return db.Produtos.findAll(nome);
	}

	public void cadastrar(Produto produto) {
		db.Produtos.add(produto);
	}

	public void alterar(Produto produto) {
		db.Produtos.update(produto);
	}
}
