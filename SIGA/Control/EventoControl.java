package Control;

import persistenceManagerFactory.PersistenceManagerFactory;
import java.util.List;
import Dominio.TipoPagamento;
import Interfaces.ITipoPagamentoRepository;
import Repositories.TipoPagamentoRepository;

public class EventoControl {

	private ITipoPagamentoRepository _tppagamento = new TipoPagamentoRepository(
			PersistenceManagerFactory.getPersistanceManager()); 
	
	public List<TipoPagamento> allTpPagamentos(){
		return _tppagamento.findAll();
	}
	
}
