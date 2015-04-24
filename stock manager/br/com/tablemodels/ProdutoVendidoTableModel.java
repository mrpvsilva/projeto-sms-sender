package com.tablemodels;

import java.text.NumberFormat;

import com.dominio.ProdutoVendido;

public class ProdutoVendidoTableModel extends DefaultTableModel<ProdutoVendido> {

	public ProdutoVendidoTableModel() {
		super(new String[] { "id", "Nome do produto", "Quant.", "Valor",
				"Total" });

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
					pv.getProduto().getValor());
		case 4:
			return NumberFormat.getCurrencyInstance().format(pv.getTotal());

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
			return Double.class;
		}
	}

}
