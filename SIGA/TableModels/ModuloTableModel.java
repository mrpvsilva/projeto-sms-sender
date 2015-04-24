package TableModels;

import java.util.List;

import Dominio.Modulo;

public class ModuloTableModel extends AbstractDefaultTableModel<Modulo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModuloTableModel() {
		this.colunas = new String[] { "ID", "Nome" };
	}

	public ModuloTableModel(List<Modulo> linhas) {
		super(linhas);
		this.colunas = new String[] { "ID", "Nome" };
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

}
