package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.EventoItem;

public class EventoItemTableModel extends DefaultTableModel<EventoItem> {

	private final static String[] colunas = new String[] { "Nome",
			"Valor unit.", "Qtd", "Subtotal", "Tipo" };

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
			return NumberFormat.getCurrencyInstance().format(ei.getSubtotal());
		case 4:
			return ei.getItem().getTipoitem().getNome();
		default:
			return null;
		}
	}

	@Override
	public long getId(int linha) {
		return linhas.get(linha).getId();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 2 || columnIndex == 3);
	}

	@Override
	public void setValueAt(Object Value, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 2:
			int v = Integer.parseInt(Value.toString());
			linhas.get(rowIndex).setQuantidade(v);
			break;
		case 3:
			String sub = Value.toString();
			linhas.get(rowIndex).setSubtotal(new BigDecimal(sub));
		default:
			break;
		}
		// avisa que os dados mudaram
		fireTableDataChanged();
	}

	@Override
	public Class getColumnClass(int columnIndex) {

		if (columnIndex == 0 || columnIndex == 4) {
			return String.class;
		} else if (columnIndex == 2) {
			return Integer.class;
		}

		return BigDecimal.class;
	}

}
