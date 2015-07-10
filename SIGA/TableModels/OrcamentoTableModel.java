package TableModels;

import java.util.List;

import Dominio.Evento;
import Extra.Extras;

public class OrcamentoTableModel extends DefaultTableModel<Evento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "Nome do evento",
			"Data de cadastro", "Data do evento", "Tipo de evento" };

	public OrcamentoTableModel() {
		super(colunas);
	}

	public OrcamentoTableModel(List<Evento> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int col) {
		Evento e = getLinhas().get(row);
		switch (col) {
		case 0:
			return e.getNome();
		case 1:
			return Extras.FormatDate(e.getDataCriacao(), "dd/MM/yyyy");
		case 2:
			return Extras.FormatDate(e.getDataEvento(), "dd/MM/yyyy");
		case 3:
			return e.getTipo();
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
		return String.class;
	}

}
