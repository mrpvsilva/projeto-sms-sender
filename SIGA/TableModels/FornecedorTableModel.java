package TableModels;

import java.util.List;

import Dominio.Fornecedor;

public class FornecedorTableModel extends DefaultTableModel<Fornecedor> {

	private final static String[] colunas = new String[] { "Nome", "CPFCNPJ",
			"Telefone", "Servi�o" };
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
	public long getId(int linha) {
		return linhas.get(linha).getId();
	}	

	@Override
	public Class getColumnClass(int column) {
		// TODO Auto-generated method stub
		return String.class;
	}

}
