package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Perfil;
import Dominio.Usuario;
import Extra.Extras;
import Interfaces.IPerfilRepository;
import Interfaces.IUsuarioRepository;
import Model.UsuarioModel;
import Repositories.PerfilRepository;
import Repositories.UsuarioRepository;

public class UsuarioControl {

	UsuarioModel usuMod = new UsuarioModel();
	private IUsuarioRepository _usuarioReposiroty = new UsuarioRepository();
	private IPerfilRepository _perfilRepository = new PerfilRepository();

	public UsuarioControl() {

	}

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

	public Perfil getPerfil(String nome) {
		return _perfilRepository.getPerfil(nome);
	}

	public String[] DDLPerfis() {

		List<Perfil> perfis = _perfilRepository.findAll();
		String[] ddl = new String[perfis.size() + 1];
		ddl[0] = "Selecione";

		for (int i = 0; i < perfis.size(); i++) {
			ddl[i + 1] = perfis.get(i).getNome();
		}

		return ddl;
	}

	/* Envia filtros para view JDTelaBuscarCli */
	public ArrayList<String> Filtros() {

		return usuMod.FiltroUsu();

	}// final do método filtros

}
