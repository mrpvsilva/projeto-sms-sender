package Interfaces;

import java.util.List;
import Dominio.TipoItem;

public interface ITipoItemRepository extends IRepositoryBase<TipoItem> {

	public List<TipoItem> findAll(String nome, String ativo);

	public TipoItem find(String nome);
}
