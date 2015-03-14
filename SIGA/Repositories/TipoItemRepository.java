package Repositories;

import Dominio.TipoItem;
import Interfaces.IPersistenceManager;
import Interfaces.ITipoItemRepository;

public class TipoItemRepository extends RepositoryBase<TipoItem> implements
		ITipoItemRepository {

	public TipoItemRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);

	}

}
