package Model;

import java.util.ArrayList;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Usuario;
import Extra.Extras;
import Interfaces.IUsuarioRepository;
import Repositories.UsuarioRepository;

public class UsuarioModel {

	private IUsuarioRepository _usuarioRepository = new UsuarioRepository(
			PersistenceManagerFactory.getPersistanceManager());

	public boolean AutenticarUsuario(UsuarioBean usuario) {

		Usuario u = _usuarioRepository.GetUsuario(usuario.getLogin(),
				usuario.getSenha());

		if (u == null)
			return false;

		Extras.setUsuarioLogado(u);

		return true;
	}

	/* Adiciona lista de possíveis filtros */
	public ArrayList<String> FiltroUsu() {
		ArrayList<String> lista = new ArrayList<String>();

		lista.add("USUARIO");
		lista.add("NOME");
		lista.add("CPF");

		return lista;
	}// final do método filtros

	public String[] DDLPerfis() {
		return new String[] { "SELECIONE", "ADMIN", "VENDEDOR" };
	}

}
