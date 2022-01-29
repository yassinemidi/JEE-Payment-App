package myapp.dao;

import java.util.ArrayList;

import javax.persistence.*;

import myapp.models.abonnement;



public class AbonnementDAO {
	
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    EntityTransaction trans ;

	public AbonnementDAO() {
		super();
		 try {
	            entityManagerFactory = Persistence.createEntityManagerFactory("exam_java");
	            entityManager = entityManagerFactory.createEntityManager();
	            trans = entityManager.getTransaction();
	           
	            
	            
	            
	        } catch(Exception e) {
	        	e.printStackTrace();
	            
	        }
	}
	
	public void close() {
		if ( entityManager != null ) entityManager.close();
        if ( entityManagerFactory != null ) entityManagerFactory.close();
	}
	

	public ArrayList<abonnement> getData(){
		return (ArrayList<abonnement>) entityManager.createQuery("from abonnement",abonnement.class).getResultList();
	}
	
	public void addAbonnement(abonnement ab) {
		trans.begin();
		entityManager.persist(ab);
		trans.commit();
	}
	
	public void updateAbonnement(abonnement ab,String nom, double price) {
		trans.begin();
		ab.setNom(nom);
		ab.setPrice(price);
		entityManager.persist(ab);
		trans.commit();
	}
	
	
	public void deleteAbonnement(abonnement ab) {
		trans.begin();
		entityManager.remove(ab);
		trans.commit();
	}
	
	public abonnement findAbonnement(int id) {
		return (abonnement) entityManager.find(abonnement.class, id);
	}
	
	
	
	

}
