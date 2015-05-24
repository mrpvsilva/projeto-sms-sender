package TableModels;

import java.util.List;

import Dominio.Perfil;

public class PerfilTableModel extends DefaultTableModel<Perfil> {

	private final static String[] colunas = new String[] { "ID", "Nome" };
	private static final long serialVersionUID = 1L;

	public PerfilTableModel() {
		super(colunas);
	}

	public PerfilTableModel(List<Perfil> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getNome();
		default:
			return null;
		}

	}

	@Override
	public int getId(int linha) {
		// TODO Auto-generated method stub
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public Perfil get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
