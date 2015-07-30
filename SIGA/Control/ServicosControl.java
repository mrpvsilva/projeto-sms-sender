package Control;

import java.util.ArrayList;
import java.util.List;

import Dominio.Item;
import Dominio.TipoCobranca;
import Dominio.TipoItem;
import Interfaces.IItemRepository;
import Interfaces.ITipoItemRepository;
import Model.ServicosModel;
import Repositories.ItemRepository;
import Repositories.TipoItemRepository;
import Util.TipoAtivo;

public class ServicosControl {

	private ServicosModel servMod = new ServicosModel();

	private IItemRepository _itemRepository = new ItemRepository();
	private ITipoItemRepository tipoItemRepository = new TipoItemRepository();

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

	public List<Item> listarTodos(String item,TipoCobranca tipo,TipoAtivo ativo) {
		return _itemRepository.findAll(item, tipo,ativo);
	}

	public Item buscarItem(long l) {
		return _itemRepository.find(l);
	}

	public String[] DDLTipoServico() {
		List<TipoItem> l = tipoItemRepository.findAll("", "Ativo");
		String[] a = new String[l.size() + 1];
		a[0] = "SELECIONE";
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
