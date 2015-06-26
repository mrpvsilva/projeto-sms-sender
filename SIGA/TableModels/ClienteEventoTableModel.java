package TableModels;

import java.util.List;

import Dominio.ClienteEvento;
import Dominio.Telefone;

public class ClienteEventoTableModel extends DefaultTableModel<ClienteEvento> {

	private final static String[] colunas = new String[] { "Nome", "CPF",
			"Telefone", "Nº conv. extras" };

	public ClienteEventoTableModel() {
		super(colunas);

	}

	public ClienteEventoTableModel(List<ClienteEvento> linhas) {
		super(colunas, linhas);

	}

	@Override
	public Object getValueAt(int row, int col) {
		ClienteEvento c = linhas.get(row);

		switch (col) {
		case 0:
			return c.getCliente().getNomeCompleto();
		case 1:
			return c.getCliente().getCpfCnpj();
		case 2:
			Telefone t = c.getCliente().getTelefones().get(0);
			return "(" + t.getDdd() + ")" + t.getNumero() + " "
					+ t.getOperadora();
		case 3:
			return c.getConvidadosextras();

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
