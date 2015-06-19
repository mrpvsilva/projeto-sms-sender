package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.Servico;

public class ServicoTableModel extends DefaultTableModel<Servico> {

	private final static String[] colunas = new String[] { "Nome", "Valor",
			"Ativo" };

	private static final long serialVersionUID = 1L;

	public ServicoTableModel() {
		super(colunas);

	}

	public ServicoTableModel(List<Servico> linhas) {
		super(colunas, linhas);
	}

	// CRUD

	@Override
	public int getId(int linha) {
		return getLinhas().get(linha).getId().intValue();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return getLinhas().get(linha).getNome();
		case 1:
			return NumberFormat.getCurrencyInstance().format(
					getLinhas().get(linha).getValorservico());
		case 2:
			return getLinhas().get(linha).isAtivo() ? "Ativo" : "Inativo";
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 1:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object Value, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			BigDecimal valor = new BigDecimal(Value.toString());
			linhas.get(rowIndex).setValorservico(valor);
			break;
		default:
			break;
		}
		// avisa que os dados mudaram
		fireTableDataChanged();
	}

	@Override
	public Class getColumnClass(int columnIndex) {

		if (columnIndex == 0) {
			return String.class;
		} else if (columnIndex == 2) {
			return Integer.class;
		} 
		return BigDecimal.class;
	}

	@Override
	public Servico get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
