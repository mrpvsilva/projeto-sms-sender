package TableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dominio.TipoItem;

public class TipoItemTableModel extends AbstractTableModel {

	private String[] colunas = new String[] { "ID", "Nome", "Ativo" };
	private List<TipoItem> linhas;

	public TipoItemTableModel() {
		linhas = new ArrayList<TipoItem>();
	}

	public TipoItemTableModel(List<TipoItem> tipoItens) {
		linhas = tipoItens;
	}

	// CRUD
	public List<TipoItem> getTipoItens() {
		return linhas;
	}

	public void setTipoItens(List<TipoItem> tipoItens) {
		linhas = tipoItens;
		this.fireTableDataChanged();
	}

	public TipoItem getTipoItem(int linha) {
		return linhas.get(linha);
	}

	public void add(TipoItem tipoItem) {
		linhas.add(tipoItem);
		this.fireTableDataChanged();
	}

	public void update(int linha, TipoItem tipoItem) {
		linhas.set(linha, tipoItem);
		this.fireTableDataChanged();
	}

	public void remove(int linha) {
		linhas.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

	//

	public String getColumnName(int num) {
		return this.colunas[num];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return linhas.get(row).getId();
		case 1:
			return linhas.get(row).getNome();
		case 2:
			return linhas.get(row).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;

		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
