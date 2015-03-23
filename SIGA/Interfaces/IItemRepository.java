package Interfaces;

import java.util.List;

import Dominio.Item;

public interface IItemRepository extends IRepositoryBase<Item> {

	public List<Item> FindAll(String campo, String txt);

}
