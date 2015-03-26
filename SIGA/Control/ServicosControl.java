package Control;

import java.util.ArrayList;
import java.util.List;

import persistenceManagerFactory.PersistenceManagerFactory;
import Dominio.Item;
import Dominio.TipoItem;
import Interfaces.IItemRepository;
import Interfaces.ITipoItemRepository;
import Model.ServicosModel;
import Repositories.ItemRepository;
import Repositories.TipoItemRepository;

public class ServicosControl {

	private ServicosModel servMod = new ServicosModel();

	private IItemRepository _itemRepository = new ItemRepository(
			PersistenceManagerFactory.getPersistanceManager());

	private ITipoItemRepository _tipoItemRepository = new TipoItemRepository(
			PersistenceManagerFactory.getPersistanceManager());

	private ITipoItemRepository tipoItemRepository = new TipoItemRepository(
			PersistenceManagerFactory.getPersistanceManager());

	public ArrayList<String> Filtros() {
		return servMod.FiltroServ();
	}

	public String cadastrar(Item item) {

		if (!_itemRepository.add(item))
			return "Falha no cadastro do Serviço";

		return null;
	}

	public List<Item> listarTodos() {
		return _itemRepository.findAll();
	}

	public List<Item> listarTodos(String campo, String txt) {
		return _itemRepository.findAll(campo, txt);
	}

	public Item buscarItem(int ID) {
		return _itemRepository.find(ID);
	}

	public String[] DDLTipoServico() {
		List<TipoItem> l = tipoItemRepository.findAll("", "Ativo");
		String[] a = new String[l.size() + 1];
		a[0] = "Selecione";
		for (int i = 0; i < l.size(); i++) {
			a[i + 1] = l.get(i).getNome();
		}
		return a;
	}

	public TipoItem buscarTipoItem(String nome) {
		return tipoItemRepository.find(nome);
	}

	public String atualizar(Item item) {
		if (!_itemRepository.update(item))
			return "Falha na atualização do serviço";

		return null;
	}

}
