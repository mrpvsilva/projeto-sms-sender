package Interfaces;

import java.util.Date;
import java.util.List;

import Dominio.Evento;
import Dominio.TiposEvento;

public interface IOrcamentoRepository extends IRepositoryBase<Evento> {

	public List<Evento> findAll(String nome, Date inicioCad, Date fimCad,
			Date inicioReal, Date fimReal, TiposEvento tipoEvento);

}
