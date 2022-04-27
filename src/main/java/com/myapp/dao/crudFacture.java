package com.myapp.dao;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.myapp.models.facture;


public class crudFacture {
	
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    EntityTransaction trans ;

	public crudFacture() {
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

	public ArrayList<facture> getData(){
		return (ArrayList<facture>) entityManager.createQuery("from facture",facture.class).getResultList();
	}
	
	public void addFacture(facture f) {
		trans.begin();
		entityManager.persist(f);
		trans.commit();
	}
	
	public void updateFacture(int id_facture,int id_user, int id_abonnement, int ispayer,Date date_debut) {
		facture f=findFacture(id_facture);
		trans.begin();
		f.setId_abonnement(id_abonnement);
		f.setId_user(id_user);
		f.setIspayer(ispayer);
		f.setDate_debut(date_debut);
		entityManager.persist(f);
		trans.commit();
	}
	
	
	public void deleteFacture(facture f) {
		trans.begin();
		entityManager.remove(f);
		trans.commit();
	}
	
	public facture findFacture(int id) {
		return (facture) entityManager.find(facture.class, id);
	}
	
	public facture findFactureUser(int id) {
		
		Query query= entityManager.createQuery("SELECT f FROM facture f WHERE id_user = :id");
		query.setParameter("id",id);
		try {
			return (facture) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}
	
	
	public void payerFacture(int id_facture) {
		facture f=findFacture(id_facture);
		trans.begin();
	
		f.setIspayer(1);
		try {
			f.setDate_debut(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		entityManager.persist(f);
		trans.commit();
	}
	
	

}
