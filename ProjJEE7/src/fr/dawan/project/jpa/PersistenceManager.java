package fr.dawan.project.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {
	

	MY_BASE("ProjJEE7");

	private EntityManagerFactory emFactory;

	private PersistenceManager(String persistenceUnitName) {
		// "jpa-example" was the value of the name attribute of the
		// persistence-unit element.
		emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}

	
	/*
	 Utilisation :
	 
	    EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    	em.getTransaction().begin();
    	em.persist(address);
    	em.getTransaction().commit();
    	em.close();
    	PersistenceManager.INSTANCE.close();
	 */

}
