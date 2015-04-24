package TableModels;

import java.text.NumberFormat;
import java.util.List;

import Dominio.Item;

public class ItemTableModel extends AbstractDefaultTableModel<Item> {

	public ItemTableModel() {
		super();
		colunas = new String[] { "ID", "Item", "Preço custo", "Preço comerc.",
				"Ativo" };
	}

	public ItemTableModel(List<Item> linhas) {
		super(linhas);
		colunas = new String[] { "ID", "Item", "Preço custo", "Preço comerc.",
				"Ativo" };
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getNome();
		case 2:
			return NumberFormat.getCurrencyInstance().format(
					getLinhas().get(rowIndex).getValorCusto());
		case 3:
			return NumberFormat.getCurrencyInstance().format(
					getLinhas().get(rowIndex).getValorComercial());
		case 4:
			return getLinhas().get(rowIndex).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;
		}
	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

}
