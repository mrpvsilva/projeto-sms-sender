package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.Item;

public class ItemTableModel extends DefaultTableModel<Item> {

	private final static String[] colunas = new String[] { "Item",
			"Preço custo", "Preço comerc.", "Tipo cobranca", "Ativo" };

	public ItemTableModel() {
		super(colunas);

	}

	public ItemTableModel(List<Item> linhas) {
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
		case 2:
			return NumberFormat.getCurrencyInstance().format(
					getLinhas().get(rowIndex).getValorComercial());
		case 3:
			return linhas.get(rowIndex).getTipocobranca();
		case 4:
			return getLinhas().get(rowIndex).isAtivo() ? "Ativo" : "Inativo";
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
		case 3:
			return String.class;
		case 4:
			return String.class;

		default:
			return BigDecimal.class;
		}
	}

}
