package persistenceManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Interfaces.IPersistenceManager;

public class PersistenceManagerFactory {

	private static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("siga");
	private static IPersistenceManager _persistanceManager = new IPersistenceManager() {
		@Override
		public EntityManager getEntityManager() {

			return factory.createEntityManager();
		}
	};

	public static IPersistenceManager getPersistanceManager() {
		try {
			return _persistanceManager;
		} catch (Exception e) {

			return null;
		}
	}

}
