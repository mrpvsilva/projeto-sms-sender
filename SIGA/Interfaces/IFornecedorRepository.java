package Interfaces;

import java.util.List;
import Dominio.Fornecedor;

public interface IFornecedorRepository extends IRepositoryBase<Fornecedor> {

	public List<Fornecedor> findAll(String coluna, String valor);
}
