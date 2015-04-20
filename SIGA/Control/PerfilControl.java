package Control;

import java.util.List;

import Dominio.Perfil;
import Interfaces.IPerfilRepository;
import Repositories.PerfilRepository;

public class PerfilControl {

	private IPerfilRepository perfilRepository;

	public PerfilControl() {
		perfilRepository = new PerfilRepository();
	}

	public List<Perfil> listarTodos() {
		return perfilRepository.findAll();
	}

	public Perfil buscarPerfil(int id) {
		return perfilRepository.find(id);
	}

	public boolean cadastrar(Perfil perfil) {
		return perfilRepository.add(perfil);
	}

	public boolean atualizar(Perfil perfil) {
		return perfilRepository.update(perfil);
	}

}
