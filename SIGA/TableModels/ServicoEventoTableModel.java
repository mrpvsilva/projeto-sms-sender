package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.EventoServico;
import Dominio.Fornecedor;

public class ServicoEventoTableModel extends DefaultTableModel<EventoServico> {

	private final static String[] colunas = new String[] { "Nome",
			"Fornecedor", "Valor", };

	public ServicoEventoTableModel() {
		super(colunas);

	}

	public ServicoEventoTableModel(List<EventoServico> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		EventoServico e = linhas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getServico().getNome();
		case 1:
			Fornecedor f = e.getServico().getExecutor();
			return f != null ? f.getNome() : "";
		case 2:
			return NumberFormat.getCurrencyInstance().format(
					e.getServico().getValorservico());
		default:
			return null;
		}
	}

	@Override
	public long getId(int linha) {
		return linhas.get(linha).getId();
	}

	@Override
	public Class getColumnClass(int column) {
		if (column == 2)
			return BigDecimal.class;

		return String.class;
	}

}
