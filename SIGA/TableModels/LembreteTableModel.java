package TableModels;

import java.util.List;

import Dominio.Lembrete;
import Extra.Extras;

public class LembreteTableModel extends DefaultTableModel<Lembrete> {

	private final static String[] colunas = new String[] { "Data", "Assunto",
			"Destinatário" };

	public LembreteTableModel() {
		super(colunas);

	}

	public LembreteTableModel(List<Lembrete> linhas) {
		super(colunas, linhas);

	}

	@Override
	public Object getValueAt(int row, int col) {
		Lembrete l = getLinhas().get(row);

		switch (col) {
		case 0:
			return Extras.FormatDate(l.getDatahora(), "dd/MM/yyyy HH:mm");
		case 1:
			return l.getAssunto();
		case 2:
			return l.getDestinatario().getUsuario();
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
