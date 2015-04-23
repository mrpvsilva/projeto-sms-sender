package com.repositorios;

import com.contexts.FornecedorContext;
import com.contexts.ProdutoContext;
import com.interfaces.IFornecedorContext;
import com.interfaces.IProdutoContexto;

public class StockEntities {

	public IProdutoContexto Produtos;
	public IFornecedorContext Fornecedores;

	public StockEntities() {
		Produtos = new ProdutoContext();
		Fornecedores = new FornecedorContext();
	}
}
