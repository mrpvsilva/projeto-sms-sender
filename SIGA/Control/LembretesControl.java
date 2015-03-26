package Control;

import java.util.ArrayList;
import java.util.List;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Lembrete;
import Dominio.Usuario;
import Extra.Extras;
import Interfaces.ILembreteRepository;
import Interfaces.IUsuarioRepository;
import Model.LembretesModel;
import Repositories.LembreteRepository;
import Repositories.UsuarioRepository;

public class LembretesControl {

	LembretesModel lembMod = new LembretesModel();

	private ILembreteRepository _lembreteRepository = new LembreteRepository(
			PersistenceManagerFactory.getPersistanceManager());
	private IUsuarioRepository _usuarioRepository = new UsuarioRepository(
			PersistenceManagerFactory.getPersistanceManager());

	/* Envia filtros para view JDTelaBuscarLemb */

	public String Cadastrar(Lembrete lembrete) {

		lembrete.setRemetente(Extras.getUsuarioLogado());

		if (!_lembreteRepository.add(lembrete))
			return "Falha no cadastro do lembrete";

		return null;
	}

	public String Atualizar(Lembrete lembrete) {

		lembrete.setRemetente(_usuarioRepository.find(2));

		if (!_lembreteRepository.update(lembrete))
			return "Falha no atualização do lembrete";

		return null;
	}

	public List<Lembrete> BuscarTodos() {
		return _lembreteRepository.findAll();
	}

	public List<Lembrete> BuscarTodos(String campo, String valor) {
		return _lembreteRepository.findAll(campo, valor);
	}

	public Usuario BuscarDestinatario(String usuario) {
		return _usuarioRepository.getUsuario(usuario);
	}

	public String[] DDLRemetentes() {
		List<Usuario> l = _usuarioRepository.findAll();
		String[] ddl = new String[l.size() + 1];
		ddl[0] = "Selecione";
		for (int i = 0; i < l.size(); i++) {
			ddl[i + 1] = l.get(i).getUsuario();
		}
		return ddl;

	}

	public Lembrete BuscarLembrete(int id) {
		return _lembreteRepository.find(id);
	}

	public String[] Filtros() {
		return lembMod.FiltroLemb();
	}// final do método filtros

}
