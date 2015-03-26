package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Usuario;
import Interfaces.IUsuarioRepository;
import Model.UsuarioBean;
import Model.UsuarioModel;
import Repositories.UsuarioRepository;

public class UsuarioControl {

	UsuarioModel usuMod = new UsuarioModel();
	private IUsuarioRepository _usuarioReposiroty = new UsuarioRepository(
			PersistenceManagerFactory.getPersistanceManager());

	public UsuarioBean Logar(UsuarioBean usuAut) {

		usuAut.setResposta(usuMod.AutenticarUsuario(usuAut));

		/* Validação da autenticação */
		if (usuAut.getResposta()) {
			return usuAut;
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.",
					"SIGA - Autenticação", JOptionPane.ERROR_MESSAGE);
			return usuAut;
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
