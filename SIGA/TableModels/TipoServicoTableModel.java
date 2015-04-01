package TableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dominio.TipoServico;

public class TipoServicoTableModel extends AbstractTableModel {

	private List<TipoServico> linhas;

	private String[] colunas = new String[] { "id", "Nome", "Ativo" };

	public TipoServicoTableModel() {
		linhas = new ArrayList<TipoServico>();
	}

	public TipoServicoTableModel(List<TipoServico> lista) {
		linhas = lista;
	}

	// CRUD

	public int getId(int linha) {
		return linhas.get(linha).getId().intValue();
	}

	public List<TipoServico> getTipoItens() {
		return linhas;
	}

	public void setTipoServico(List<TipoServico> tipoServicos) {
		linhas = tipoServicos;
		this.fireTableDataChanged();
	}

	public TipoServico getTipoServico(int linha) {
		return linhas.get(linha);
	}

	public void add(TipoServico tipoServico) {
		linhas.add(tipoServico);
		this.fireTableDataChanged();
	}

	public void update(int linha, TipoServico tipoServico) {
		linhas.set(linha, tipoServico);
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
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return linhas.get(linha).getId();
		case 1:
			return linhas.get(linha).getNome();
		case 2:
			return linhas.get(linha).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
