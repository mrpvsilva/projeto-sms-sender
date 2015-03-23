package persistenceManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Interfaces.IPersistenceManager;

public class PersistenceManagerFactory {

	private static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("siga");
	private static EntityManager _entityManager = factory.createEntityManager();
	private static IPersistenceManager _persistanceManager = new IPersistenceManager() {
		@Override
		public EntityManager GetEntityManager() {

			return _entityManager;
		}
	};

	public static IPersistenceManager getPersistanceManager() {
		try {
			return _persistanceManager;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

}
