package Interfaces;

import java.util.List;

public interface IRepositoryBase<E> {

	public boolean Add(E entity);

	public void Remove(long id);

	public boolean Update(E entity);

	public E GetById(long id);

	public List<E> FindAll();
}
