package com.repositorios;

import com.contexts.FornecedorContext;
import com.contexts.ProdutoContext;
import com.contexts.VendaContext;
import com.interfaces.IFornecedorContext;
import com.interfaces.IProdutoContexto;
import com.interfaces.IVendaContext;

public class StockEntities {

	public IProdutoContexto Produtos;
	public IFornecedorContext Fornecedores;
	public IVendaContext Vendas;

	public StockEntities() {
		Produtos = new ProdutoContext();
		Fornecedores = new FornecedorContext();
		Vendas = new VendaContext();
	}
}
