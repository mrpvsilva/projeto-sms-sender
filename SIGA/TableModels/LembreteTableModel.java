package TableModels;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;

import View.BuscarLembretes;
import Dominio.Lembrete;
import Extra.Extras;

public class LembreteTableModel extends DefaultTableModel<Lembrete> {

	private final static String[] colunas = new String[] { "Lido", "Remetente",
			"Assunto", "Data do lembrete" };

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
			return l.isLido() ? new ImageIcon(
					Toolkit.getDefaultToolkit().getImage(
							BuscarLembretes.class.getResource("/Img/ok_.png")))
					: null;
		case 1:
			return l.getRemetente().getUsuario();
		case 2:
			return l.getAssunto();
		case 3:
			return Extras.FormatDate(l.getDatahora(), "dd/MM/yyyy HH:mm");
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
		if (column == 0)
			return ImageIcon.class;

		return String.class;
	}

}
