package com.myapp.models;


import java.util.Date;

import javax.persistence.*;


@Entity @Table(name="factures")
public class facture {

	@Override
	public String toString() {
		return "facture [id=" + id + ", id_user=" + id_user + ", id_abonnement=" + id_abonnement + ", ispayer="
				+ ispayer + ", date_debut=" + date_debut + "]";
	}

	public facture() {
		super();
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int id_user;
	private int id_abonnement;
	private int ispayer;
	
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date_debut;
	
	
	public facture(int id, int id_user, int id_abonnement, int ispayer,Date date_debut) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_abonnement = id_abonnement;
		this.ispayer = ispayer;
		this.date_debut=date_debut;
	}

	public facture(int id_user, int id_abonnement, int ispayer,Date date_debut) {
		super();
		this.id_user = id_user;
		this.id_abonnement = id_abonnement;
		this.ispayer = ispayer;

		this.date_debut=date_debut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_abonnement() {
		return id_abonnement;
	}

	public void setId_abonnement(int id_abonnement) {
		this.id_abonnement = id_abonnement;
	}

	public int getIspayer() {
		return ispayer;
	}

	public void setIspayer(int ispayer) {
		this.ispayer = ispayer;
	}
	
	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	
	
	
	

}
