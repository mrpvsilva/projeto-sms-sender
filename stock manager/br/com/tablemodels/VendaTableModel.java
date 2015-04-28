package com.tablemodels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.dominio.Venda;

public class VendaTableModel extends DefaultTableModel<Venda> {

	private static final String[] colunas = new String[] { "id",
			"Data da venda", "Valor Total", "Valor desconto" };

	public VendaTableModel() {
		super(colunas);
		// TODO Auto-generated constructor stub
	}

	public VendaTableModel(List<Venda> linhas) {
		super(colunas, linhas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int row, int col) {
		Venda v = getLinhas().get(row);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		switch (col) {
		case 0:
			return v.getVendaId();
		case 1:
			return format.format(v.getDatahoravenda().getTime());
		case 2:
			return NumberFormat.getCurrencyInstance().format(v.getValortotal());
		case 3:
			return NumberFormat.getCurrencyInstance().format(v.getDesconto());
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
			return Calendar.class;
		default:
			return BigDecimal.class;
		}
	}

}
