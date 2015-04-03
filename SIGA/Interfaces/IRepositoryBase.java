package Interfaces;

import java.util.List;

public interface IRepositoryBase<E> {

	public boolean add(E entity);

	public void remove(long id);

	public boolean update(E entity);

	public E find(long id);

	public List<E> findAll();

	public void open();

	public void close();

}
