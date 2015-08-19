package Interfaces;

import java.util.List;

import Dominio.Cliente;

public interface IClienteRepository extends IRepositoryBase<Cliente> {

	public List<Cliente> findAll(String valor, String campo);	
	
	public List<Cliente> findAll(String valor, String campo,int page,int total);

	public Cliente find(String cpfcnpj);
	
	public long countCliente(String valor, String campo);
	
	

}
