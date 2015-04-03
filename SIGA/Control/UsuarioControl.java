package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Usuario;
import Extra.Extras;
import Interfaces.IUsuarioRepository;
import Model.UsuarioModel;
import Repositories.UsuarioRepository;

public class UsuarioControl {

	UsuarioModel usuMod = new UsuarioModel();
	private IUsuarioRepository _usuarioReposiroty = new UsuarioRepository();

	public Usuario Logar(Usuario usuario) {

		Usuario u = _usuarioReposiroty.getUsuario(usuario.getUsuario(),
				usuario.getSenha());
		if (u != null) {
			Extras.setUsuarioLogado(u);
			return u;
		} else {

			return null;
		}

	}// final do método Logar

	public String Cadastrar(Usuario usuario) {

		if (!_usuarioReposiroty.add(usuario)) {
			return "Falha no cadastro do usuário";
		}

		return null;
	}

	public List<Usuario> BuscarTodos() {
		return _usuarioReposiroty.findAll();
	}

	public List<Usuario> BuscarTodos(String campo, String value) {
		return _usuarioReposiroty.findAll(campo, value);
	}

	public Usuario BuscarUsuario(int id) {
		return _usuarioReposiroty.find(id);
	}

	public String Atualizar(Usuario usuario) {

		if (!_usuarioReposiroty.update(usuario))
			return "Falha na atualização do usuário";

		return null;
	}

	public String[] DDLPerfis() {
		return usuMod.DDLPerfis();
	}

	/* Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros() {

		return usuMod.FiltroUsu();

	}// final do método filtros

}
