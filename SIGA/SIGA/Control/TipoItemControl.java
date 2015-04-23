package Control;

import java.util.List;

import Dominio.TipoItem;
import Interfaces.ITipoItemRepository;
import Repositories.TipoItemRepository;

public class TipoItemControl {

	private ITipoItemRepository _tipoItemRepository = new TipoItemRepository();

	public String cadastra(TipoItem tipoItem) {

		if (!_tipoItemRepository.add(tipoItem))
			return "Falha no cadastro do tipo de serviço";
		return null;
	}

	public String atualizar(TipoItem tipoItem) {
		if (!_tipoItemRepository.update(tipoItem))
			return "Falha na atualização do tipo de serviço";
		return null;
	}

	public List<TipoItem> ListarTodos() {
		return _tipoItemRepository.findAll();
	}

	public List<TipoItem> ListarTodos(String nome, String ativo) {
		return _tipoItemRepository.findAll(nome, ativo);
	}

	public TipoItem buscarTipoItem(int id) {
		return _tipoItemRepository.find(id);
	}

}
