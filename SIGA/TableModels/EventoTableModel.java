package TableModels;

import java.util.List;

import Dominio.Evento;
import Extra.Extras;

public class EventoTableModel extends DefaultTableModel<Evento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "Nome do evento",
			"Data do cadastro", "Tipo de evento" };

	public EventoTableModel() {
		super(colunas);
	}

	public EventoTableModel(List<Evento> linhas) {
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
			return e.getTipo();
		default:
			return null;
		}
	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public Evento get(long id) {
		for (Evento evento : linhas) {
			if (evento.getId() == id)
				return evento;
		}

		return null;
	}

}
