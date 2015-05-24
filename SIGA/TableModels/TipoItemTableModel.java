package TableModels;

import java.util.List;

import Dominio.TipoItem;

public class TipoItemTableModel extends DefaultTableModel<TipoItem> {

	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "ID", "Nome",
			"Ativo" };

	public TipoItemTableModel() {
		super(colunas);

	}

	public TipoItemTableModel(List<TipoItem> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return getLinhas().get(row).getId();
		case 1:
			return getLinhas().get(row).getNome();
		case 2:
			return getLinhas().get(row).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;

		}
	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public TipoItem get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
