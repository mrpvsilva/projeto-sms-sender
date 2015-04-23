package TableModels;

import java.util.List;

import Dominio.Perfil;

public class PerfilTableModel extends AbstractDefaultTableModel<Perfil> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PerfilTableModel() {
		super();
		super.colunas = new String[] { "ID", "Nome" };
	}

	public PerfilTableModel(List<Perfil> linhas) {
		super(linhas);
		super.colunas = new String[] { "ID", "Nome" };
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

}
