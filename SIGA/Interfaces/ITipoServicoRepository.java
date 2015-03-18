package Interfaces;

import java.util.List;

import Dominio.TipoServico;

public interface ITipoServicoRepository extends IRepositoryBase<TipoServico> {

	public TipoServico getTipoByName(String nome);

	public List<TipoServico> FindAll(boolean ativo);

}
