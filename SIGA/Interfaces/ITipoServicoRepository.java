package Interfaces;

public interface ITipoServicoRepository<E> extends IRepositoryBase<E> {

	public E getTipoByName(String nome);

}
