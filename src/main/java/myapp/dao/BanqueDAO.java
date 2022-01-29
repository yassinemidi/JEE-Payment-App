package myapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import myapp.models.banque;


public class BanqueDAO {
	
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    EntityTransaction trans ;

	public BanqueDAO() {
		super();
		 try {
	            entityManagerFactory = Persistence.createEntityManagerFactory("exam_java");
	            entityManager = entityManagerFactory.createEntityManager();
	            trans = entityManager.getTransaction();
	           
	            
	            
	            
	        } catch(Exception e) {
	        	e.printStackTrace();
	            
	        }
	}
	
	public banque findCompte(String nb) {

		Query query= entityManager.createQuery("SELECT b FROM banque b WHERE num_card = :em ");
		query.setParameter("em",nb);
		try {
			banque b= (banque) query.getSingleResult();
			if(b!=null) return b;
			else return null;

		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	public void SoldeSous(String num_carte,double s) {
		banque b=findCompte(num_carte);
		trans.begin();
		b.setSolde(b.getSolde()-s);
		entityManager.persist(b);
		trans.commit();
		
	}
	
	
	
	
	
	
	
}
