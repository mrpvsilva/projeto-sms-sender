package com.controllers;

import java.util.List;

import com.dominio.Fornecedor;
import com.repositorios.StockEntities;

public class FornecedorController {

	private StockEntities db;

	public FornecedorController() {
		db = new StockEntities();
	}

	public List<Fornecedor> listarTodos() {
		return db.Fornecedores.findAll();
	}

	public List<Fornecedor> listarTodos(String nome) {
		return db.Fornecedores.findAll(nome);
	}

	public void cadastrar(Fornecedor fornecedor) {
		db.Fornecedores.add(fornecedor);
	}

	public void alterar(Fornecedor fornecedor) {
		db.Fornecedores.update(fornecedor);
	}

}
