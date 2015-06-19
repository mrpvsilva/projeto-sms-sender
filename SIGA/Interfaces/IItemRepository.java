package Interfaces;

import java.util.List;

import Dominio.Item;

public interface IItemRepository extends IRepositoryBase<Item> {

	public List<Item> findAll(String campo, String txt);
	
	public List<Item> findByTipo(String tipoItem);
	
	public List<Item> findAll(boolean ativo);

	

}
