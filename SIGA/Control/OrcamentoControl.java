package Control;

import java.util.Date;
import java.util.List;

import Dominio.Cliente;
import Dominio.Evento;
import Dominio.Item;
import Dominio.TipoItem;
import Dominio.TiposEvento;
import Interfaces.IClienteRepository;
import Interfaces.IItemRepository;
import Interfaces.IOrcamentoRepository;
import Interfaces.ITipoItemRepository;
import Repositories.ClienteRepository;
import Repositories.ItemRepository;
import Repositories.OrcamentoRepository;
import Repositories.TipoItemRepository;

public class OrcamentoControl {

	private IOrcamentoRepository _orcamentoRepository;
	private IClienteRepository _clienterepository;
	private IItemRepository _itemRepository;
	private ITipoItemRepository _tipoItemRepository;

	public OrcamentoControl() {
		_orcamentoRepository = new OrcamentoRepository();
		_clienterepository = new ClienteRepository();
		_itemRepository = new ItemRepository();
		_tipoItemRepository = new TipoItemRepository();
	}

	public List<Evento> listarTodos() {
		return _orcamentoRepository.findAll();
	}

	public List<Evento> listarTodos(String nome, Date inicio, Date fim,
			TiposEvento tipoEvento) {
		return _orcamentoRepository.findAll(nome, inicio, fim, tipoEvento);
	}

	public boolean cadastrar(Evento evento) {
		return _orcamentoRepository.add(evento);
	}

	public boolean alterar(Evento evento) {
		return _orcamentoRepository.update(evento);
	}

	public Cliente buscarCliente(String cpfcnpj) {
		return _clienterepository.find(cpfcnpj);
	}

	public List<Item> buscarItens(String tipoItem) {
		return _itemRepository.findByTipo(tipoItem);
	}

	public Object[] DDLTipoItens() {
		return _tipoItemRepository.DDLTipoItens().toArray();
	}
}
