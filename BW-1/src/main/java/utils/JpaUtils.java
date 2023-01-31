package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JpaUtils {

	private static final String persistenceUnit = "BW-1";
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
	protected static final EntityManager em = emf.createEntityManager();
	protected static final EntityTransaction t = em.getTransaction();


	static {
		try {
			emf = Persistence
					.createEntityManagerFactory(persistenceUnit);
		} catch (Throwable ex) {
			System.err.println("Initial EntityManagerFactory creation failed."
					+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

}