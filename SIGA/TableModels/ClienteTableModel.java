package TableModels;

import java.util.List;

import Dominio.Cliente;
import Dominio.Telefone;

public class ClienteTableModel extends DefaultTableModel<Cliente> {

	private final static String[] colunas = new String[] { "Nome", "CPF", "RG",
			"Email", "Telefone" };

	public ClienteTableModel() {
		super(colunas);
	}

	public ClienteTableModel(List<Cliente> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int row, int col) {
		Cliente c = getLinhas().get(row);

		switch (col) {
		case 0:
			return c.getNomeCompleto();
		case 1:
			return c.getCpfCnpj();
		case 2:
			return c.getRg();
		case 3:
			return c.getEmail();
		case 4:
			Telefone t = c.getTelefones().get(0);
			return "(" + t.getDdd() + ")" + t.getNumero() + " "
					+ t.getOperadora();
		default:
			return null;
		}
	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public Cliente get(long id) {

		
		for (Cliente c : linhas) {
			if (c.getId() == id)
				return c;
		}

		return null;
	}

}
