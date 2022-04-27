package com.myapp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.authentification;
import com.myapp.dao.crudAbonnement;
import com.myapp.dao.crudFacture;
import com.myapp.mail.JavaMailUtil;
import com.myapp.models.abonnement;
import com.myapp.models.facture;
import com.myapp.models.user;

/**
 * Servlet implementation class agent
 */
@WebServlet("/agent")
public class agent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		authentification at=new authentification();

		Hashtable<String, String> tmp ;
		abonnement tmp_ab;
		facture tmp_fac;
		crudFacture crdf=new crudFacture();
		crudAbonnement crdab=new crudAbonnement();
		ArrayList<Hashtable<String, String>> list_abonnees=new ArrayList<Hashtable<String,String>>();
		ArrayList<user> list_users=at.getData();
		for (user user : list_users) {
			if(user.getType_user()==1) {
				tmp_fac =crdf.findFactureUser(user.getId());
				tmp_ab=crdab.findAbonnement(tmp_fac.getId_abonnement());
				tmp = new Hashtable<String, String>();
				
				
				tmp.put("user_id",Integer.toString(user.getId()));
				tmp.put("facture_id",Integer.toString(tmp_fac.getId()));
				tmp.put("user_nom",user.getNom() );
				tmp.put("user_prenom",user.getPrenom() );
				tmp.put("user_email",user.getEmail() );
				tmp.put("abonnement_nom",tmp_ab.getNom() );
				tmp.put("abonnement_prix",Double.toString(tmp_ab.getPrice()));
				tmp.put("ispayee",Integer.toString(tmp_fac.getIspayer()) );
				
				list_abonnees.add(tmp);
			}
			
			
			
		}
		request.getSession().setAttribute("list_abonnees",list_abonnees);
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/agent/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		crudFacture crdf=new crudFacture();
		crdf.payerFacture(Integer.parseInt( request.getParameter("id_facture")));
	
		
		
		Hashtable<String, String> tmp ;
		abonnement tmp_ab;
		facture tmp_fac;
		authentification at=new authentification();
		crudAbonnement crdab=new crudAbonnement();
		ArrayList<Hashtable<String, String>> list_abonnees=new ArrayList<Hashtable<String,String>>();
		ArrayList<user> list_users=at.getData();
		for (user user : list_users) {
			if(user.getType_user()==1) {
				tmp_fac =crdf.findFactureUser(user.getId());
				tmp_ab=crdab.findAbonnement(tmp_fac.getId_abonnement());
				tmp = new Hashtable<String, String>();
				
				
				tmp.put("user_id",Integer.toString(user.getId()));
				tmp.put("facture_id",Integer.toString(tmp_fac.getId()));
				tmp.put("user_nom",user.getNom() );
				tmp.put("user_prenom",user.getPrenom() );
				tmp.put("user_email",user.getEmail() );
				tmp.put("abonnement_nom",tmp_ab.getNom() );
				tmp.put("abonnement_prix",Double.toString(tmp_ab.getPrice()));
				tmp.put("ispayee",Integer.toString(tmp_fac.getIspayer()) );
				
				list_abonnees.add(tmp);
			}
			
			
			
		}
		
		tmp_fac=crdf.findFacture(Integer.parseInt( request.getParameter("id_facture")));
		tmp_ab=crdab.findAbonnement(tmp_fac.getId_abonnement());
		user tmp_user=at.findUser(tmp_fac.getId_user());
		
		try {
			JavaMailUtil.sendMail(tmp_user.getEmail(), tmp_user, tmp_fac, tmp_ab);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("tmp_fac",tmp_fac);
		request.getSession().setAttribute("tmp_ab",tmp_ab);
		request.getSession().setAttribute("tmp_user",tmp_user);
		request.getSession().setAttribute("today",LocalDate.now().toString());
		
		
		request.getSession().setAttribute("list_abonnees",list_abonnees);
		this.getServletContext().getRequestDispatcher("/WEB-INF/agent/recu.jsp").forward(request, response);

	}

}
