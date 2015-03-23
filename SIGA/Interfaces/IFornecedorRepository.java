package Interfaces;

import java.util.List;
import Dominio.Fornecedor;

public interface IFornecedorRepository extends IRepositoryBase<Fornecedor> {

	public List<Fornecedor> FindAll(String coluna, String valor);
}
