package Interfaces;

import Dominio.Usuario;

public interface IUsuarioRepository extends IRepositoryBase<Usuario> {

	public Usuario GetUsuario(String usuario, String senha);

}
