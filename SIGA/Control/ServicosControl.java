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
		TipoItem tipoItem = _tipoItemRepository.GetById(1);

		Item item = new Item(servico.getNomeitem(), servico.getDescricaoitem(),
				servico.getVlrcustoitem(), servico.getVlrcomercialitem(),
				servico.isAtivoitem(), tipoItem);

		if (!_itemRepository.Add(item))
			return "Falha no cadastro do Serviço";

		return null;
	}

	public List<Item> BuscarTodos() {
		return _itemRepository.FindAll();
	}

	public List<Item> BuscarTodos(String campo, String txt) {
		return _itemRepository.FindAll(campo, txt);
	}

	public Item BuscarItem(int ID) {
		return _itemRepository.GetById(ID);
	}

	public String Atualizar(Item item) {
		if (!_itemRepository.Update(item))
			return "Falha na atualização do serviço";

		return null;
	}

}
