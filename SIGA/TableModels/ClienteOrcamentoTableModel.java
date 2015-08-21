package TableModels;

import java.util.List;

import Dominio.ClienteEvento;
import Dominio.Telefone;

public class ClienteOrcamentoTableModel extends DefaultTableModel<ClienteEvento> {

	private final static String[] colunas = new String[] { "Nome", "Telefone",
			"Email" };

	public ClienteOrcamentoTableModel() {
		super(colunas);

	}

	public ClienteOrcamentoTableModel(List<ClienteEvento> linhas) {
		super(colunas, linhas);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int col) {
		ClienteEvento c = linhas.get(row);

		switch (col) {
		case 0:
			return c.getCliente().getNomeCompleto();
		case 1:
			Telefone t = c.getCliente().getTelefones().get(0);
			return "(" + t.getDdd() + ")" + t.getNumero() + " "
					+ t.getOperadora();
		case 2:
			return c.getCliente().getEmail();

		default:
			return null;
		}

	}

	@Override
	public long getId(int linha) {
		return linhas.get(linha).getId();
	}

	@Override
	public Class getColumnClass(int column) {
		switch (column) {
		case 3:
			return Integer.class;
		default:
			return String.class;
		}

	}

}
