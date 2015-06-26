package TableModels;

import java.util.List;

import Dominio.Perfil;

public class PerfilTableModel extends DefaultTableModel<Perfil> {

	private final static String[] colunas = new String[] { "Nome" };
	private static final long serialVersionUID = 1L;

	public PerfilTableModel() {
		super(colunas);
	}

	public PerfilTableModel(List<Perfil> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getLinhas().get(rowIndex).getNome();
	}

	@Override
	public long getId(int linha) {
		// TODO Auto-generated method stub
		return getLinhas().get(linha).getId();
	}

	

	@Override
	public Class getColumnClass(int column) {
		return String.class;
	}

}
