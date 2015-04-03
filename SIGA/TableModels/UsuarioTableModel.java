package TableModels;

import java.util.List;
import Dominio.Usuario;

public class UsuarioTableModel extends AbstractDefaultTableModel<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioTableModel() {
		super();
		super.colunas = new String[] { "ID", "Usuário", "CPF", "Perfil" };
	}

	public UsuarioTableModel(List<Usuario> linhas) {
		super(linhas);
		super.colunas = new String[] { "ID", "Usuário", "CPF", "Perfil" };

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
			return getLinhas().get(rowIndex).getPerfil();
		default:
			return null;
		}

	}

	@Override
	public int getId(int linha) {
		return (int) getLinhas().get(linha).getId();
	}

}
