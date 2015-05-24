package TableModels;

import java.util.List;

import Dominio.Modulo;

public class ModuloTableModel extends DefaultTableModel<Modulo> {

	private final static String[] colunas = new String[] { "ID", "Nome" };
	private static final long serialVersionUID = 1L;

	public ModuloTableModel() {
		super(colunas);
	}

	public ModuloTableModel(List<Modulo> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return getLinhas().get(row).getId();
		case 1:
			return getLinhas().get(row).getNome();

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
	public Modulo get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
