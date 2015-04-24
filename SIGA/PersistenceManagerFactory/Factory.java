package PersistenceManagerFactory;

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

//	public static void renewFactory() {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				factory.close();
//				factory = Persistence.createEntityManagerFactory("siga");
//			}
//		}).start();
//
//	}
}
