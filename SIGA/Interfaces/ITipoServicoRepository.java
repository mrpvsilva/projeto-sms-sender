package Interfaces;

import Dominio.TipoServico;

public interface ITipoServicoRepository extends IRepositoryBase<TipoServico> {

	public TipoServico getTipoByName(String nome);

}
