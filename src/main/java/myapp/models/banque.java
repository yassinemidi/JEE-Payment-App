package myapp.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="banque")
public class banque {

	public banque() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String num_card;
	private double solde;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date_expiration;
	private String last_num;
	
	public String getLast_num() {
		return last_num;
	}

	public void setLast_num(String last_num) {
		this.last_num = last_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum_card() {
		return num_card;
	}

	public void setNum_card(String num_card) {
		this.num_card = num_card;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Date getDate_expiration() {
		return date_expiration;
	}

	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}


	

	public banque(int id, String num_card, double solde, Date date_expiration,String last_num) {
		super();
		this.id = id;
		this.num_card = num_card;
		this.solde = solde;
		this.date_expiration = date_expiration;
		this.last_num = last_num;
	}
}
