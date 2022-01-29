package myapp.dao;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import javax.persistence.*;

import myapp.models.facture;
import myapp.models.user;

public class UserDAO {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    EntityTransaction trans ;
	
	public UserDAO() {
		super();
		
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("exam_java");
            entityManager = entityManagerFactory.createEntityManager();
            trans = entityManager.getTransaction();
           
            
            
            
        } catch(Exception e) {
        	e.printStackTrace();
            
        }
		
	}
	
	
	public ArrayList<user> getData(){
		return (ArrayList<user>) entityManager.createQuery("from user",user.class).getResultList();
	}
	
	
	
	public user login(String email,String password) {
		
		
		
		Query query= entityManager.createQuery("SELECT u FROM user u WHERE email = :em and password= :psd");
		query.setParameter("em",email);
		query.setParameter("psd",password);
		
		try {
			return (user) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
		
        
        
				
		
	}
	
	
	public boolean register(String nom,String prenom,String email,String password,int id_abonnement) {
		try {
			trans.begin();
			user u=new user(nom,prenom,1,email,password);
			
			entityManager.persist(u);
			trans.commit();
			
			Query query= entityManager.createQuery("SELECT u FROM user u WHERE email = :em and password= :psd");
			query.setParameter("em",email);
			query.setParameter("psd",password);
			DateTimeFormatter format = DateTimeFormatter.ofPattern( "uuuu-MM-dd" ) ;
			LocalDate date = LocalDate.parse( LocalDate.now().minusMonths(2).toString() , format ) ;
			facture f=new facture(((user) query.getSingleResult()).getId(),id_abonnement,0,new SimpleDateFormat("yyyy-MM-dd").parse(date.toString()));
			trans.begin();
			entityManager.persist(f);
			trans.commit();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
		
		
		
	}
	
	public user findUser(int id) {
		return (user) entityManager.find(user.class, id);
	}
	
	
	public void close() {
		if ( entityManager != null ) entityManager.close();
        if ( entityManagerFactory != null ) entityManagerFactory.close();
	}

}
