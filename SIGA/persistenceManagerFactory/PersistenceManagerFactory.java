package persistenceManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManagerFactory {

	private static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("siga");

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
