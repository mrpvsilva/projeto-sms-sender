package TableModels;

import java.util.List;
import Dominio.TelefoneFornecedor;

public class TelefoneTableModel extends AbstractDefaultTableModel<TelefoneFornecedor> {
	private static final long serialVersionUID = 1L;

	public TelefoneTableModel() {
		super();
		super.colunas = new String[] { "ID", "DDD", "Número", "Operadora" };
	}

	public TelefoneTableModel(List<TelefoneFornecedor> telefones) {
		super(telefones);
		super.colunas = new String[] { "ID", "DDD", "Número", "Operadora" };
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getDdd();
		case 2:
			return getLinhas().get(rowIndex).getNumero();
		case 3:
			return getLinhas().get(rowIndex).getOperadora();
		default:
			return null;
		}

	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

	

}
