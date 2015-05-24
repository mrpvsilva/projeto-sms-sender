package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.EventoItem;

public class EventoItemTableModel extends DefaultTableModel<EventoItem> {

	private final static String[] colunas = new String[] { "Nome", "Valor",
			"Qtd", "Incluir" };

	public EventoItemTableModel() {
		super(colunas);
	}

	public EventoItemTableModel(List<EventoItem> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int col) {
		EventoItem ei = linhas.get(row);

		switch (col) {
		case 0:
			return ei.getItem().getNome();
		case 1:
			return NumberFormat.getCurrencyInstance().format(
					ei.getItem().getValorComercial());
		case 2:
			return ei.getQuantidade();
		case 3:
			return ei.isIncluso();
		default:
			return null;
		}
	}

	@Override
	public int getId(int linha) {
		return (int) linhas.get(linha).getId();
	}

	@Override
	public EventoItem get(long id) {
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 2:
			return true;
		case 3:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object Value, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 2:
			int v = Integer.parseInt(Value.toString());
			linhas.get(rowIndex).setQuantidade(v);
			break;
		case 3:
			boolean b = (boolean) Value;
			linhas.get(rowIndex).setIncluso(b);
			break;
		default:
			break;
		}
		// avisa que os dados mudaram
		fireTableDataChanged();
	}

	@Override
	public Class getColumnClass(int columnIndex) {

		if (columnIndex == 0) {
			return String.class;
		} else if (columnIndex == 2) {
			return Integer.class;
		} else if (columnIndex == 3) {
			return Boolean.class;
		}

		return BigDecimal.class;
	}

}
