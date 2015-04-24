package TableModels;

import java.util.List;
import Dominio.TipoItem;

public class TipoItemTableModel extends AbstractDefaultTableModel<TipoItem> {

	private static final long serialVersionUID = 1L;

	public TipoItemTableModel() {
		super();
		this.colunas = new String[] { "ID", "Nome", "Ativo" };

	}

	public TipoItemTableModel(List<TipoItem> tipoItens) {
		super(tipoItens);
		this.colunas = new String[] { "ID", "Nome", "Ativo" };
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

}
