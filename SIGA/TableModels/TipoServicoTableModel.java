package TableModels;

import java.util.List;

import Dominio.Servico;

public class TipoServicoTableModel extends DefaultTableModel<Servico> {

	private final static String[] colunas = new String[] { "id", "Nome",
			"Ativo" };
	private static final long serialVersionUID = 1L;

	public TipoServicoTableModel() {
		super(colunas);

	}

	public TipoServicoTableModel(List<Servico> linhas) {
		super(colunas, linhas);
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

	@Override
	public Servico get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
