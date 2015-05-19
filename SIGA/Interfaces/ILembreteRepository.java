package Interfaces;

import java.util.Date;
import java.util.List;

import Dominio.Lembrete;

public interface ILembreteRepository extends IRepositoryBase<Lembrete> {

	public List<Lembrete> findAll(Date dataInicial, Date dataFinal,
			String destinatario);

}
