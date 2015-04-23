package Interfaces;

import java.util.List;

import Dominio.Perfil;

public interface IPerfilRepository extends IRepositoryBase<Perfil> {

	public Perfil getPerfil(String nome);

	public List<Perfil> findAllWithRoot();

}
