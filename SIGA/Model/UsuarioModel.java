package Model;

import java.util.ArrayList;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Usuario;
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

		System.out.println(u.getId());
		return true;
	}

	/* Adiciona lista de possíveis filtros */
	public ArrayList<String> FiltroUsu() {
		ArrayList<String> lista = new ArrayList<String>();

		lista.add("LOGIN");
		lista.add("NOME");
		lista.add("PERFIL");

		return lista;
	}// final do método filtros

}
