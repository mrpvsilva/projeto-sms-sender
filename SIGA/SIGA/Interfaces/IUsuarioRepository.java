package Interfaces;

import java.util.List;

import Dominio.Usuario;

public interface IUsuarioRepository extends IRepositoryBase<Usuario> {

	public Usuario getUsuario(String usuario, String senha);

	public Usuario getUsuario(String usuario);

	public List<Usuario> findAll(String campo, String value);

}
