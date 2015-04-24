package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Modulo;
import Dominio.Perfil;
import Dominio.Permissao;
import Interfaces.IModuloRepository;
import Interfaces.IPerfilRepository;
import Repositories.ModuloRepository;
import Repositories.PerfilRepository;

public class ModuloControl {

	private IModuloRepository moduloReposity;
	private IPerfilRepository perfilRepository;

	public ModuloControl() {
		moduloReposity = new ModuloRepository();
		perfilRepository = new PerfilRepository();
	}

	public List<Modulo> listarTodos() {
		return moduloReposity.findAll();
	}

	public List<Modulo> listarTodos(String nome) {
		return moduloReposity.findAll(nome);
	}

	private List<Permissao> buscarPermissoes(Modulo modulo) {

		List<Permissao> permissoes = new ArrayList<Permissao>();
		List<Perfil> perfis = perfilRepository.findAllWithRoot();

		for (Perfil perfil : perfis) {
			permissoes.add(new Permissao(modulo, perfil));
		}

		return permissoes;
	}

	public boolean cadastrar(Modulo modulo) {
		modulo.setPermissoes(buscarPermissoes(modulo));
		return moduloReposity.add(modulo);
	}

	public boolean alterar(Modulo modulo) {
		return moduloReposity.update(modulo);
	}

}
