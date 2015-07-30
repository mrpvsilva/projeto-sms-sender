package Control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import Dominio.Cliente;
import Dominio.Evento;
import Dominio.EventoItem;
import Dominio.EventoServico;
import Dominio.Item;
import Dominio.Servico;
import Dominio.TiposEvento;
import Interfaces.IClienteRepository;
import Interfaces.IItemRepository;
import Interfaces.IOrcamentoRepository;
import Interfaces.IServicoRepository;
import Interfaces.ITipoItemRepository;
import Repositories.ClienteRepository;
import Repositories.ItemRepository;
import Repositories.OrcamentoRepository;
import Repositories.ServicoRepository;
import Repositories.TipoItemRepository;
import Util.GerarRelatorio;
import Util.TipoRelatorio;

public class OrcamentoControl {

	private IOrcamentoRepository _orcamentoRepository;
	private IClienteRepository _clienterepository;
	private IItemRepository _itemRepository;
	private ITipoItemRepository _tipoItemRepository;
	private IServicoRepository _servicoRepository;

	public OrcamentoControl() {
		_orcamentoRepository = new OrcamentoRepository();
		_clienterepository = new ClienteRepository();
		_itemRepository = new ItemRepository();
		_tipoItemRepository = new TipoItemRepository();
		_servicoRepository = new ServicoRepository();

	}

	public List<Evento> listarTodos() {
		return _orcamentoRepository.findAll();
	}

	public List<Evento> listarTodos(String nome, Date inicioCad, Date fimCad,
			Date inicioReal, Date fimReal,

			TiposEvento tipoEvento) {
		return _orcamentoRepository.findAll(nome, inicioCad, fimCad,
				inicioReal, fimReal, tipoEvento);
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

//	public void buscarEventoItens(Evento evento) {
//		List<Item> itens = _itemRepository.findAll(true);	
//
//		for (Item item : itens) {			
//			evento.addItem(new EventoItem(evento, item));
//		}
//		
//	}

	public void buscarServicos(Evento evento) {
		List<Servico> servicos = _servicoRepository.findAll(true);
		
		for (Servico servico : servicos) {			
			evento.addServico(new EventoServico(evento, servico));
		}
		
	}

	public List<Item> pesquisarItens(String tipoItem) {
		if (tipoItem.equals("Todos"))
			return _itemRepository.findAll(true);
		else
			return _itemRepository.findByTipo(tipoItem);
	}

	public Object[] DDLTipoItens() {
		return _tipoItemRepository.DDLTipoItens().toArray();
	}
	
	public void gerarOrcamento(long id,TiposEvento tipoEvento){
		Map<String,Object> map = new HashMap<String, Object>();		
		map.put("IDEVENTO", id);			
		try {
			GerarRelatorio.gerarRelatorio(TipoRelatorio.ORCAMENTO_FORMATURA, map );
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, "Falha ao gerar orçamento");
		}
	}
}
