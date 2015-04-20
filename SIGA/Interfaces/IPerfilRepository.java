package Interfaces;

import Dominio.Perfil;

public interface IPerfilRepository extends IRepositoryBase<Perfil> {

	public Perfil getPerfil(String nome);

}
