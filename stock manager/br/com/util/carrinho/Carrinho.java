package com.util.carrinho;

import com.dominio.Produto;
import com.dominio.Venda;

public class Carrinho {

	private static Venda venda;

	public static void add(Produto produto, int quantidade) {
		if (venda == null)
			venda = new Venda();
		venda.addProduto(produto, quantidade);
	}

	public static Venda getCarrinho() {
		return venda != null ? venda : new Venda();
	}

	public static void limparCarrinho() {
		venda = new Venda();
	}
}
