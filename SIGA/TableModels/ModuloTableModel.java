package TableModels;

import java.util.List;

import Dominio.Modulo;

public class ModuloTableModel extends DefaultTableModel<Modulo> {

	private final static String[] colunas = new String[] { "Nome" };
	private static final long serialVersionUID = 1L;

	public ModuloTableModel() {
		super(colunas);
	}

	public ModuloTableModel(List<Modulo> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int column) {
		return getLinhas().get(row).getNome();
	}

	@Override
	public long getId(int linha) {
		return getLinhas().get(linha).getId();
	}

	
	@Override
	public Class getColumnClass(int column) {
		return String.class;
	}

}
