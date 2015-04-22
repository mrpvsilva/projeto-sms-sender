package com.contexts;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import com.interfaces.IBaseContext;
import com.jpautil.JpaUtil;

public abstract class BaseContext<T> implements IBaseContext<T> {

	protected EntityManager manager;

	public BaseContext() {
		// manager = JpaUtil.getEntityManager();
	}

	@Override
	public boolean add(T entity) {
		try {
			open();
			beginTransaction();
			manager.persist(entity);
			commit();
			return true;
		} catch (Exception ex) {
			rollback();
		} finally {
			close();
		}
		return false;
	}

	@Override
	public boolean remove(T entity) {
		try {
			open();
			beginTransaction();
			manager.remove(entity);
			commit();
			return true;
		} catch (Exception ex) {
			rollback();
		} finally {
			close();
		}
		return false;
	}

	@Override
	public boolean update(T entity) {
		try {
			open();
			beginTransaction();
			manager.merge(entity);
			commit();
			return true;
		} catch (Exception ex) {
			rollback();
		} finally {
			close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		try {
			open();
			return manager.createQuery("from " + typeClass().getName())
					.getResultList();
		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(long entityId) {
		try {
			open();
			return (T) manager.find(typeClass(), entityId);
		} catch (Exception ex) {

		} finally {
			close();
		}
		return null;
	}

	@Override
	public void beginTransaction() {
		manager.getTransaction().begin();
	}

	@Override
	public void commit() {
		manager.getTransaction().commit();

	}

	@Override
	public void rollback() {
		manager.getTransaction().rollback();

	}

	public void open() {
		manager = JpaUtil.getEntityManager();
	}

	@Override
	public void close() {
		// manager.close();
		JpaUtil.closeEntityManager();
	}

	private Class<?> typeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
