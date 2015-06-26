package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.Item;

public class ItemEventoTableModel extends DefaultTableModel<Item> {

	private final static String[] colunas = new String[] { "Item",
			"Preço unit." };

	public ItemEventoTableModel() {
		super(colunas);

	}

	public ItemEventoTableModel(List<Item> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getNome();
		case 1:
			return NumberFormat.getCurrencyInstance().format(
					getLinhas().get(rowIndex).getValorCusto());
		default:
			return null;
		}
	}

	@Override
	public long getId(int linha) {
		return getLinhas().get(linha).getId();
	}

	

	@Override
	public Class getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return BigDecimal.class;
		default:
			return null;

		}

	}

}
