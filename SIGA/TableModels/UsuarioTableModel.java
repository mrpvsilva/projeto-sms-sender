package TableModels;

import java.util.List;

import Dominio.Usuario;

public class UsuarioTableModel extends DefaultTableModel<Usuario> {

	private static final long serialVersionUID = 1L;
	private final static String[] colunas = new String[] { "ID", "Usuário",
			"CPF", "Perfil" };

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
			return getLinhas().get(rowIndex).getId();
		case 1:
			return getLinhas().get(rowIndex).getUsuario();
		case 2:
			return getLinhas().get(rowIndex).getCpf();
		case 3:
			return getLinhas().get(rowIndex).getPerfil().getNome();
		default:
			return null;
		}

	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

	@Override
	public Usuario get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
