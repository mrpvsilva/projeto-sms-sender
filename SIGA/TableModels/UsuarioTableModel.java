package TableModels;

import java.util.List;

import Dominio.Usuario;

public class UsuarioTableModel extends DefaultTableModel<Usuario> {

	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "Usuário", "CPF",
			"Perfil" };

	public UsuarioTableModel() {
		super(colunas);
	}

	public UsuarioTableModel(List<Usuario> linhas) {
		super(colunas, linhas);

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return getLinhas().get(rowIndex).getUsuario();
		case 1:
			return getLinhas().get(rowIndex).getCpf();
		case 2:
			return getLinhas().get(rowIndex).getPerfil().getNome();

		default:
			return null;
		}

	}

	@Override
	public long getId(int linha) {
		return getLinhas().get(linha).getId();
	}

	@Override
	public Class getColumnClass(int column) {
		// TODO Auto-generated method stub
		return String.class;
	}

}
