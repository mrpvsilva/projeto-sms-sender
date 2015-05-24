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
			"Data do orçamento", "Tipo de evento" };

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
			return Extras.FormatDate(e.getDataCriacao(),"dd/MM/yyyy");
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
		// TODO Auto-generated method stub
		return null;
	}

}
