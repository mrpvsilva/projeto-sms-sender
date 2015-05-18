package Interfaces;

import java.util.List;

import Dominio.Cliente;

public interface IClienteRepository extends IRepositoryBase<Cliente> {
	
	
	public List<Cliente> findAll(String valor,String campo);

}
