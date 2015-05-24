package Interfaces;

import java.util.Date;
import java.util.List;

import Dominio.Evento;
import Dominio.TiposEvento;

public interface IOrcamentoRepository extends IRepositoryBase<Evento> {

	public List<Evento> findAll(String nome, Date inicio, Date fim,
			TiposEvento tipoEvento);

}
