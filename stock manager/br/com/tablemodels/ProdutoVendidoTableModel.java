package com.tablemodels;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.dominio.ProdutoVendido;

public class ProdutoVendidoTableModel extends DefaultTableModel<ProdutoVendido> {

	private static final String[] colunas = new String[] { "id", "Produto",
			"Quant.", "Valor Unit.", "Subtotal" };

	public ProdutoVendidoTableModel() {
		super(colunas);

	}

	@Override
	public Object getValueAt(int row, int col) {
		ProdutoVendido pv = find(row);
		switch (col) {
		case 0:
			return pv.getProdutovendidoId();
		case 1:
			return pv.getProduto().getNome();
		case 2:
			return pv.getQuantidade();
		case 3:
			return NumberFormat.getCurrencyInstance().format(
					pv.getProduto().getValorUnitario());
		case 4:
			return NumberFormat.getCurrencyInstance().format(pv.getSubTotal());

		default:
			return null;
		}
	}

	@Override
	public Class getColumnClass(int col) {
		switch (col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		default:
			return BigDecimal.class;
		}
	}

}
