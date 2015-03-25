package Interfaces;

import java.util.List;

import Dominio.Lembrete;

public interface ILembreteRepository extends IRepositoryBase<Lembrete> {

	public List<Lembrete> FindAll(String coluna, String valor);

}
