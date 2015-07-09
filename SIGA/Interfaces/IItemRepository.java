package Interfaces;

import java.util.List;

import Util.TipoAtivo;
import Dominio.Item;
import Dominio.TipoCobranca;

public interface IItemRepository extends IRepositoryBase<Item> {

	public List<Item> findAll(String item, TipoCobranca tipo,TipoAtivo ativo);
	
	public List<Item> findByTipo(String tipoItem);
	
	public List<Item> findAll(boolean ativo);

	

}
