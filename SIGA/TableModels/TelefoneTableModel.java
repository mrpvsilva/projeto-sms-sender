package TableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dominio.Telefone;

public class TelefoneTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Telefone> _telefones;
	private String[] colunas = new String[] { "Id", "ddd", "Número",
			"Operadora" };
	private final int ID = 0;
	private final int DDD = 1;
	private final int NUMERO = 2;
	private final int OPERADORA = 3;

	public TelefoneTableModel() {
		_telefones = new ArrayList<Telefone>();

	}

	public TelefoneTableModel(List<Telefone> telefones) {
		_telefones = telefones;
	}

	public String getColumnName(int num) {
		return this.colunas[num];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return _telefones.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case ID:
			return _telefones.get(rowIndex).getId();
		case DDD:
			return _telefones.get(rowIndex).getDdd();
		case NUMERO:
			return _telefones.get(rowIndex).getNumero();
		case OPERADORA:
			return _telefones.get(rowIndex).getOperadora();
		default:
			return null;
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	// CRUD TELEFONES
	public void add(Telefone telefone) {
		_telefones.add(telefone);
		this.fireTableDataChanged();
	}

	public void update(int linha, Telefone telefone) {
		_telefones.set(linha, telefone);
		this.fireTableDataChanged();
	}

	public void remove(int linha) {
		_telefones.remove(linha);
		this.fireTableRowsDeleted(linha, linha);

	}

	public List<Telefone> getTelefones() {
		return _telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		_telefones = telefones;
		this.fireTableDataChanged();
	}

	public Telefone getTelefone(int linha) {
		return _telefones.get(linha);
	}

}
