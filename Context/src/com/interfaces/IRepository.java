package com.interfaces;

import java.util.List;

public interface IRepository<E> {

	public boolean Add(E entity);

	public void Remove(long id);

	public boolean Update(E entity);

	public E GetById(long id);

	public List<E> FindAll();
}
