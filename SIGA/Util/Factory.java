package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

	private static EntityManagerFactory factory;

	public static EntityManager createEntityManager() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("siga");

		return factory.createEntityManager();
	}
	
	

}
