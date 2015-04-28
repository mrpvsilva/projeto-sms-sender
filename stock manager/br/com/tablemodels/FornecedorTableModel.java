package com.tablemodels;

import java.util.List;

import com.dominio.Fornecedor;

public class FornecedorTableModel extends DefaultTableModel<Fornecedor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] colunas = new String[] { "id", "Nome",
			"Telefone", "Email", "Site" };

	public FornecedorTableModel() {
		super(colunas);
	}

	public FornecedorTableModel(List<Fornecedor> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Class getColumnClass(int col) {
		switch (col) {
		case 0:
			return Integer.class;
		default:
			return String.class;
		}

	}

	@Override
	public Object getValueAt(int row, int col) {
		Fornecedor f = getLinhas().get(row);

		switch (col) {
		case 0:
			return f.getFornecedorId();
		case 1:
			return f.getNome();
		case 2:
			return f.getTelefones().get(0).toString();
		case 3:
			return f.getEmail();
		case 4:
			return f.getSite();
		default:
			return null;

		}
	}

	// @Override
	// public long getId(int linha) {
	// return getLinhas().get(linha).getFornecedorId();
	// }

}
