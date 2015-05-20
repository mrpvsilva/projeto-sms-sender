package Interfaces;

import java.util.Date;
import java.util.List;

import Dominio.Lembrete;
import Dominio.Usuario;

public interface ILembreteRepository extends IRepositoryBase<Lembrete> {

	public List<Lembrete> findAll(Usuario destinatario);

	public List<Lembrete> findAll(Date dataInicial, Date dataFinal,
			String assunto, Usuario destinatario);

}
