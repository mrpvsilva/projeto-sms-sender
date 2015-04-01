package Interfaces;

import java.util.List;

import Dominio.TipoServico;

public interface ITipoServicoRepository extends IRepositoryBase<TipoServico> {

	public TipoServico findByName(String nome);

	public List<TipoServico> findAll(boolean ativo);
	
	public List<TipoServico> findAll(String nome, String ativo);

}
