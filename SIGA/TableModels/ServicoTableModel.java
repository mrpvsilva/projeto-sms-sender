package TableModels;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import Dominio.Servico;

public class ServicoTableModel extends DefaultTableModel<Servico> {

	private final static String[] colunas = new String[] { "Nome", "Valor",
			"Tipo de cobrança","Ativo" };

	private static final long serialVersionUID = 1L;

	public ServicoTableModel() {
		super(colunas);

	}

	public ServicoTableModel(List<Servico> linhas) {
		super(colunas, linhas);
	}

	// CRUD

	@Override
	public long getId(int linha) {
		return getLinhas().get(linha).getId();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Servico s = linhas.get(linha);
		System.out.println(linha+": "+s.isAtivo());
		
		
		switch (coluna) {
		case 0:
			return s.getNome();
		case 1:
			return NumberFormat.getCurrencyInstance().format(
					s.getValorservico());
		case 2:
			return s.getTipocobranca();
		case 3:
			return s.isAtivo() ? "Ativo" : "Inativo";
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

		switch (columnIndex) {
		case 1:
			return BigDecimal.class;
		default:
			return String.class;
		}

	}

	

}
