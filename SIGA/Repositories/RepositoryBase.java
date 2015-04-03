package Repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import persistenceManagerFactory.PersistenceManagerFactory;
import Interfaces.IPersistenceManager;
import Interfaces.IRepositoryBase;

public abstract class RepositoryBase<E> implements IRepositoryBase<E> {

	protected EntityManager entityManager;
	private IPersistenceManager persistenceManager;

	public RepositoryBase() {
		this.persistenceManager = PersistenceManagerFactory
				.getPersistanceManager();
	}

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
			ex.printStackTrace();
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
			ex.printStackTrace();
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
			ex.printStackTrace();
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
			close();
			return l;
		} catch (Exception ex) {
			ex.printStackTrace();
			close();
			return null;
		}
	}

	@Override
	public void open() {
		entityManager = persistenceManager.getEntityManager();
	}

	@Override
	public void close() {
		entityManager.close();
	}

	private Class<?> GetTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
