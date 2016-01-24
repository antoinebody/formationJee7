package fr.dawan.project.jpa.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.dawan.project.jpa.Marque;
import fr.dawan.project.jpa.PersistenceManager;

@Singleton
public class MarqueDao {

	private EntityManager em;

	public MarqueDao() {
		em = PersistenceManager.MY_BASE.getEntityManager();
	}

	public void inserer(Marque m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Marque> lister() {
		return (List<Marque>) em.createQuery("From Marque").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Marque> lister(int debut, int nbElts) {
		return (List<Marque>) em.createQuery("From Marque").setFirstResult(debut).setMaxResults(nbElts).getResultList();

	}

	public Marque rechercher(long id) {

		return em.find(Marque.class, id);
	}

	public Marque rechercher(String nom) {
		//return (Marque) em.createQuery("Select m From Marque m WHERE m.nomMarque=:p1").setParameter("p1", nom).getSingleResult();
		return (Marque) em.createQuery("Select m From Marque m WHERE m.nomMarque='dell'").getSingleResult();
	}

}