package com.main.TipoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.applications.TipoItemApplication;
import com.domain.TipoItem;

public class TableModelTipoItem extends AbstractTableModel {

	private final int COL_ID = 0;
	private final int COL_NOME = 1;
	private final int COL_ATIVO = 2;
	private String[] colunas = new String[] { "Código", "Nome", "Ativo" };

	// lista dos produtos que serão exibidos
	private List<TipoItem> tipoItens;

	public TableModelTipoItem() {
		tipoItens = new TipoItemApplication().FindAll();
	}

	public int getRowCount() {
		return tipoItens.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		// retorna a classe que representa a coluna
		if (columnIndex == COL_ID) {
			return Integer.class;
		} else if (columnIndex == COL_NOME) {
			return String.class;
		} else if (columnIndex == COL_ATIVO) {
			return Boolean.class;
		}
		return String.class;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		TipoItem p = tipoItens.get(rowIndex);
		if (columnIndex == COL_ID) {
			return p.getId();
		} else if (columnIndex == COL_NOME) {
			return p.getNome();
		} else if (columnIndex == COL_ATIVO) {
			return p.isAtivo();
		}
		return "";
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// pega o produto da linha
		TipoItem p = tipoItens.get(rowIndex);
		if (columnIndex == COL_ID) {
			p.setId(Integer.parseInt(aValue.toString()));
		} else if (columnIndex == COL_NOME) {
			p.setNome(aValue.toString());
		} else if (columnIndex == COL_ATIVO) {
			p.setAtivo(Boolean.parseBoolean(aValue.toString()));
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void Inserir(TipoItem tipoItem) {
		tipoItens.add(tipoItem);
		fireTableDataChanged();
	}

	public TipoItem GetTipoItem(int index) {
		return tipoItens.get(index);
	}

	public void Delete(TipoItem tipo) {
		tipoItens.remove(tipo);
		fireTableDataChanged();
	}

	public void Editar(int rowIndex, TipoItem tipo) {
		this.setValueAt(tipo.getNome(), rowIndex, COL_NOME);
		this.setValueAt(tipo.isAtivo(), rowIndex, COL_ATIVO);

		 fireTableDataChanged();

	}

	public void Refresh() {
		tipoItens = new TipoItemApplication().FindAll();
		fireTableDataChanged();
	}
}
