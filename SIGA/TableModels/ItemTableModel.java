package TableModels;

import java.text.NumberFormat;
import java.util.List;

import Dominio.Item;

public class ItemTableModel extends DefaultTableModel<Item> {

	private final static String[] colunas = new String[] { "ID", "Item",
			"Preço custo", "Preço comerc.", "Ativo" };

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

	@Override
	public Item get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
