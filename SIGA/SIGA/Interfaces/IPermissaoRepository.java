package Interfaces;

import java.util.List;

import Dominio.Permissao;

public interface IPermissaoRepository extends IRepositoryBase<Permissao> {

	public Permissao find(String nomeModulo, long idPerfil);

	public List<Permissao> findAll(long idPerfil);

}
