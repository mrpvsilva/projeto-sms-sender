package com.tablemodels;

import java.util.List;

import com.dominio.Telefone;

public class TelefoneTableModel extends DefaultTableModel<Telefone> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelefoneTableModel() {
		super(new String[] { "id", "DDD", "Número", "Operadora" });
		// TODO Auto-generated constructor stub
	}

	public TelefoneTableModel(List<Telefone> linhas) {
		super(new String[] { "id", "DDD", "Número", "Operadora" }, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Telefone t = getLinhas().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return t.getTelefoneId();
		case 1:
			return t.getDdd();
		case 2:
			return t.getNumero();
		case 3:
			return t.getOperadora();
		default:
			return null;
		}
	}

//	@Override
//	public long getId(int linha) {
//		return getLinhas().get(linha).getTelefoneId();
//	}

	@Override
	public Class getColumnClass(int col) {
		switch (col) {
		case 3:
			return String.class;
		default:
			return Integer.class;
		}
	}
}
