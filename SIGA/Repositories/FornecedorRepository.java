package Repositories;

import Dominio.Fornecedor;
import Interfaces.IFornecedorRepository;
import Interfaces.IPersistenceManager;

public class FornecedorRepository extends RepositoryBase<Fornecedor> implements	IFornecedorRepository {

	public FornecedorRepository(IPersistenceManager persistenceManager) {
		super(persistenceManager);
	}

}