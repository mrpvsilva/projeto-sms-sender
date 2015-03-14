package Repositories;

import Dominio.Item;
import Interfaces.IItemRepository;
import Interfaces.IPersistenceManager;

public class ItemRepository extends RepositoryBase<Item> implements
		IItemRepository {

	public ItemRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}

}
