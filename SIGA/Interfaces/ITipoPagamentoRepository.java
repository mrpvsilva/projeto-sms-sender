package Interfaces;

import java.util.List;
import Dominio.TipoPagamento;

public interface ITipoPagamentoRepository extends IRepositoryBase<TipoPagamento>{

	public List<TipoPagamento> allTpPagamentos(String coluna, String valor);
	
}
