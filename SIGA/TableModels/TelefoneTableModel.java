package TableModels;

import java.util.List;

import Dominio.Telefone;

public class TelefoneTableModel extends DefaultTableModel<Telefone> {
	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "DDD", "N�mero",
			"Operadora" };

	public TelefoneTableModel() {
		super(colunas);
	}

	public TelefoneTableModel(List<Telefone> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getDdd();
		case 1:
			return getLinhas().get(rowIndex).getNumero();
		case 2:
			return getLinhas().get(rowIndex).getOperadora();			
		default:
			return null;
		}

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
