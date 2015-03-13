package Interfaces;
import Dominio.Usuario;

public interface IUsuarioRepository<E> extends IRepositoryBase<E> {

	public Usuario GetUsuario(String usuario, String senha);

}
