package Control;

import java.util.List;

import Dominio.Evento;
import Interfaces.IOrcamentoRepository;
import Repositories.OrcamentoRepository;

public class OrcamentoControl {

	private IOrcamentoRepository _orcamentoRepository;

	public OrcamentoControl() {
		_orcamentoRepository = new OrcamentoRepository();
	}
	
	
	public List<Evento> listarTodos(){
		return _orcamentoRepository.findAll();
	}

}
