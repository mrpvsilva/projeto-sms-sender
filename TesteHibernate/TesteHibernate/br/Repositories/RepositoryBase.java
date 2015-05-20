package Repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import Interfaces.IRepositoryBase;
import Util.Factory;

public abstract class RepositoryBase<E> implements IRepositoryBase<E> {

	protected EntityManager entityManager;

	public RepositoryBase() {
		entityManager = Factory.createEntityManager();
	}

	@Override
	public boolean add(E entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
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

			entityManager.getTransaction().begin();
			E entity = (E) entityManager.find(GetTypeClass(), id);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
		}

	}

	@Override
	public boolean update(E entity) {
		try {

			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(long id) {
		try {

			return (E) entityManager.find(GetTypeClass(), id);

		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		try {
			
			return entityManager
					.createQuery("from " + GetTypeClass().getName())
					.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void open() {
	}

	private Class<?> GetTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
