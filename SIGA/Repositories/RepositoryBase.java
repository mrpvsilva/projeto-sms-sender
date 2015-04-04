package Repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import persistenceManagerFactory.PersistenceManagerFactory;
import Interfaces.IRepositoryBase;

public abstract class RepositoryBase<E> implements IRepositoryBase<E> {

	protected EntityManager entityManager;

	@Override
	public boolean add(E entity) {
		try {
			open();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			close();
			return true;
		} catch (Exception ex) {

			entityManager.getTransaction().rollback();
			close();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(long id) {
		try {
			open();
			entityManager.getTransaction().begin();
			E entity = (E) entityManager.find(GetTypeClass(), id);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			close();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			close();

		}

	}

	@Override
	public boolean update(E entity) {
		try {
			open();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			close();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(long id) {
		try {
			open();
			E e = (E) entityManager.find(GetTypeClass(), id);
			close();
			return e;

		} catch (Exception ex) {
			close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		try {
			open();
			List<E> l = entityManager.createQuery(
					"from " + GetTypeClass().getName()).getResultList();
			clear();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			close();
			return null;
		}
	}

	@Override
	public void open() {
		entityManager = PersistenceManagerFactory.getEntityManager();
	}

	@Override
	public void clear() {
		entityManager = null;
	}

	@Override
	public void close() {
		if (entityManager != null)
			entityManager.close();
	}

	private Class<?> GetTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
