package com.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class DefaultTableModel<E> extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<E> linhas;
	protected String[] colunas;

	public DefaultTableModel() {
		this.linhas = new ArrayList<E>();
	}

	public DefaultTableModel(List<E> linhas) {
		this.linhas = linhas;
	}

	// crud
	public void add(E Entity) {
		linhas.add(Entity);
		this.fireTableDataChanged();
	}

	public void update(int linha, E Entity) {
		linhas.set(linha, Entity);
		this.fireTableDataChanged();
	}

	public void remove(int linha) {
		linhas.remove(linha);
		this.fireTableRowsDeleted(linha, linha);

	}

	public List<E> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<E> Entitys) {
		linhas = Entitys;
		this.fireTableDataChanged();
	}

	public E find(int linha) {
		return linhas.get(linha);
	}

	public abstract long getId(int linha);

	//

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
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

}
