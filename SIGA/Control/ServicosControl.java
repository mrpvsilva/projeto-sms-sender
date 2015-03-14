package Control;

import java.util.ArrayList;

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

	/* Envia filtros para view JDTelaBuscarServ */
	public ArrayList<String> Filtros() {

		return servMod.FiltroServ();

	}// final do método filtros

	public String Cadastrar(ServicosBean servico) {
		TipoItem tipoItem = _tipoItemRepository.GetById(1);

		Item item = new Item(servico.getNomeitem(), servico.getDescricaoitem(),
				servico.getVlrcustoitem(), servico.getVlrcomercialitem(),
				servico.isAtivoitem(), tipoItem);

		if (!_itemRepository.Add(item))
			return "Falha no cadastro do Serviço";

		return null;
	}
}
