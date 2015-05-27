package TableModels;

import java.util.List;
import Dominio.Fornecedor;

public class FornecedorTableModel extends DefaultTableModel<Fornecedor> {

	private final static String[] colunas = new String[] { "Nome", "CPFCNPJ",
			"Telefone", "Tipo serviço" };
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
			return linhas.get(rowIndex).getNome();
		case 1:
			return linhas.get(rowIndex).getCpfcnpj();
		case 2:
			return TelefoneFormater(linhas.get(rowIndex));
		case 3:
			return linhas.get(rowIndex).getTipoServico().getNome();
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
		return (int) linhas.get(linha).getId();
	}

	@Override
	public Fornecedor get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
