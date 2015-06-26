package TableModels;

import java.util.List;

import Dominio.Permissao;

public class PermissaoTableModel extends DefaultTableModel<Permissao> {

	private final static String[] colunas = new String[] { "ID", "Formulário",
			"Visualizar", "Cadastrar", "Alterar", "Excluir", "Imprimir" };

	public PermissaoTableModel() {
		super(colunas);
	}

	public PermissaoTableModel(List<Permissao> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getModulo().getNome();
		case 2:
			return getLinhas().get(rowIndex).isVisualizar();
		case 3:
			return getLinhas().get(rowIndex).isCadastrar();
		case 4:
			return getLinhas().get(rowIndex).isAlterar();
		case 5:
			return getLinhas().get(rowIndex).isExcluir();
		case 6:
			return getLinhas().get(rowIndex).isImprimir();
		default:
			return null;
		}

	}

	@Override
	public Class getColumnClass(int columnIndex) {
		// retorna a classe que representa a coluna
		if (columnIndex == 0) {
			return Integer.class;
		} else if (columnIndex == 1) {
			return String.class;
		}
		return Boolean.class;
	}

	@Override
	public void setValueAt(Object Value, int rowIndex, int columnIndex) {
		// pega o produto da linha
		Permissao p = getLinhas().get(rowIndex);
		boolean valor = (Boolean) Value;

		if (columnIndex == 2) {
			p.setVisualizar(valor);
		} else if (columnIndex == 3) {
			p.setCadastrar(valor);
		} else if (columnIndex == 4) {
			p.setAlterar(valor);
		} else if (columnIndex == 5) {
			p.setExcluir(valor);
		} else if (columnIndex == 6) {
			p.setImprimir(valor);
		}

		// avisa que os dados mudaram
		fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0 || columnIndex == 1)
			return false;

		return true;
	}

	@Override
	public long getId(int linha) {
		return getLinhas().get(linha).getId();
	}

	
	

}
