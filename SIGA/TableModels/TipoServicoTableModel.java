package TableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dominio.TipoServico;

public class TipoServicoTableModel extends
		AbstractDefaultTableModel<TipoServico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoServicoTableModel() {
		super();
		colunas = new String[] { "id", "Nome", "Ativo" };

	}

	public TipoServicoTableModel(List<TipoServico> lista) {
		super(lista);
		colunas = new String[] { "id", "Nome", "Ativo" };
	}

	// CRUD

	@Override
	public int getId(int linha) {
		return getLinhas().get(linha).getId().intValue();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getLinhas().get(linha).getId();
		case 1:
			return getLinhas().get(linha).getNome();
		case 2:
			return getLinhas().get(linha).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;
		}
	}

}
