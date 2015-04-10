package Repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import persistenceManagerFactory.Factory;
import Interfaces.IRepositoryBase;

public abstract class RepositoryBase<E> implements IRepositoryBase<E> {

	protected EntityManager entityManager;

	public RepositoryBase() {
		entityManager = Factory.createEntityManager();
	}

	@Override
	public boolean add(E entity) {
		try {
			open();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
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
			entityManager.close();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
		}

	}

	@Override
	public boolean update(E entity) {
		try {
			open();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(long id) {
		try {
			open();
			E e = (E) entityManager.find(GetTypeClass(), id);
			return e;
		} catch (Exception ex) {
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
			entityManager.close();
			Factory.renewFactory();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void open() {
		if (!entityManager.isOpen())
			entityManager = Factory.createEntityManager();
	}

	private Class<?> GetTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
