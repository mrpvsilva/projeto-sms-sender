package com.tablemodels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import com.dominio.Produto;

public class ProdutoTableModel extends DefaultTableModel<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] colunas = new String[] { "Id", "Nome",
			"Custo unit.", "Valor unit.", "Quantidade" };

	public ProdutoTableModel() {
		super(colunas);
	}

	public ProdutoTableModel(List<Produto> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Class getColumnClass(int col) {
		switch (col) {
		case 1:
			return String.class;
		case 2:
			return BigDecimal.class;
		case 3:
			return BigDecimal.class;
		default:
			return Integer.class;
		}

	}

	@Override
	public Object getValueAt(int row, int col) {
		Produto p = getLinhas().get(row);
		switch (col) {
		case 0:
			return p.getProdutoId();
		case 1:
			return p.getNome();
		case 2:
			return NumberFormat.getCurrencyInstance().format(p.getCusto());
		case 3:
			return NumberFormat.getCurrencyInstance().format(p.getValorUnitario());
		case 4:
			return p.getQuantidade();

		default:
			return null;

		}
	}

	// @Override
	// public long getId(int linha) {
	// return getLinhas().get(linha).getProdutoId();
	// }

}
