package com.implementations;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.interfaces.IRepository;

public class RepositoryIplement<E> implements IRepository<E> {

	protected EntityManager entityManager;

	public RepositoryIplement() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("teste");
		entityManager = factory.createEntityManager();
	}

	@Override
	public boolean Add(E entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
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
	public void Remove(long id) {
		try {
			entityManager.getTransaction().begin();
			E entity = (E) entityManager.find(GetTypeClass(), id);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}

	}

	@Override
	public boolean Update(E entity) {
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
	public E GetById(long id) {
		try {
			return (E) entityManager.find(GetTypeClass(), id);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> FindAll() {
		try {
			return entityManager
					.createQuery("from " + GetTypeClass().getName())
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private Class<?> GetTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
