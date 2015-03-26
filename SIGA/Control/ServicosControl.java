package Control;

import java.util.ArrayList;
import java.util.List;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Item;
import Dominio.TipoItem;
import Interfaces.IItemRepository;
import Interfaces.ITipoItemRepository;
import Model.ServicosBean;
import Model.ServicosModel;
import Repositories.ItemRepository;
import Repositories.TipoItemRepository;

public class ServicosControl {

	private ServicosModel servMod = new ServicosModel();
	private IItemRepository _itemRepository = new ItemRepository(
			PersistenceManagerFactory.getPersistanceManager());
	private ITipoItemRepository _tipoItemRepository = new TipoItemRepository(
			PersistenceManagerFactory.getPersistanceManager());

	public ArrayList<String> Filtros() {
		return servMod.FiltroServ();
	}

	public String Cadastrar(ServicosBean servico) {
		TipoItem tipoItem = _tipoItemRepository.find(1);

		Item item = new Item(servico.getNomeitem(), servico.getDescricaoitem(),
				servico.getVlrcustoitem(), servico.getVlrcomercialitem(),
				servico.isAtivoitem(), tipoItem);

		if (!_itemRepository.add(item))
			return "Falha no cadastro do Serviço";

		return null;
	}

	public List<Item> BuscarTodos() {
		return _itemRepository.findAll();
	}

	public List<Item> BuscarTodos(String campo, String txt) {
		return _itemRepository.findAll(campo, txt);
	}

	public Item BuscarItem(int ID) {
		return _itemRepository.find(ID);
	}

	public String Atualizar(Item item) {
		if (!_itemRepository.update(item))
			return "Falha na atualização do serviço";

		return null;
	}

}
