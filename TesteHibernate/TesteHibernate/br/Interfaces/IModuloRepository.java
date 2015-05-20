package Interfaces;

import java.util.List;

import Dominio.Modulo;

public interface IModuloRepository extends IRepositoryBase<Modulo> {

	public List<Modulo> findAll(String nome);

}
