package com.interfaces;

import java.util.List;

public interface IBaseContext<T> {

	public boolean add(T entity);

	public boolean remove(T entity);

	public boolean update(T entity);

	public List<T> findAll();

	public T find(long entityId);

	public void beginTransaction();

	public void commit();

	public void rollback();

	public void open();

	public void close();

}
