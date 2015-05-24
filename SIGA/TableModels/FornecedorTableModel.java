package TableModels;

import java.util.List;
import Dominio.Fornecedor;

public class FornecedorTableModel extends DefaultTableModel<Fornecedor> {

	private final static String[] colunas = new String[] { "ID", "Nome",
			"CPFCNPJ", "Telefone" };
	private static final long serialVersionUID = 1L;

	public FornecedorTableModel() {
		super(colunas);

	}

	public FornecedorTableModel(List<Fornecedor> linhas) {
		super(colunas, linhas);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getNome();
		case 2:
			return getLinhas().get(rowIndex).getCpfcnpj();
		case 3:
			return TelefoneFormater(getLinhas().get(rowIndex));
		default:
			return null;
		}
	}

	private String TelefoneFormater(Fornecedor f) {
		return "(" + f.getTelefones().get(0).getDdd() + ") "
				+ f.getTelefones().get(0).getNumero() + " "
				+ f.getTelefones().get(0).getOperadora();
	}

	@Override
	public int getId(int linha) {
		// TODO Auto-generated method stub
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public Fornecedor get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
