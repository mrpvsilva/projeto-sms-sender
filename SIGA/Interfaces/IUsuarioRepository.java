package Interfaces;

import java.util.List;

import Dominio.Usuario;

public interface IUsuarioRepository extends IRepositoryBase<Usuario> {

	public Usuario GetUsuario(String usuario, String senha);

	public List<Usuario> FindAll(String campo, String value);

}
