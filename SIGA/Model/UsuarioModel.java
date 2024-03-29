package Model;

import java.util.ArrayList;

import Dominio.Usuario;
import Extra.Extras;
import Interfaces.IUsuarioRepository;
import Repositories.UsuarioRepository;

public class UsuarioModel {

	private IUsuarioRepository _usuarioRepository = new UsuarioRepository();

	public boolean AutenticarUsuario(UsuarioBean usuario) {

		Usuario u = _usuarioRepository.getUsuario(usuario.getLogin(),
				usuario.getSenha());

		if (u == null)
			return false;

		Extras.setUsuarioLogado(u);

		return true;
	}

	/* Adiciona lista de poss�veis filtros */
	public ArrayList<String> FiltroUsu() {
		ArrayList<String> lista = new ArrayList<String>();

		lista.add("USUARIO");
		lista.add("NOME");
		lista.add("CPF");

		return lista;
	}// final do m�todo filtros

	

}
