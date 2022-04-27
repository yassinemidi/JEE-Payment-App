package com.myapp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.crudAbonnement;
import com.myapp.dao.crudFacture;
import com.myapp.mail.JavaMailUtil;
import com.myapp.models.abonnement;
import com.myapp.models.facture;
import com.myapp.models.user;
import com.myapp.serviceBancaire.banqueService;

/**
 * Servlet implementation class client
 */
@WebServlet("/client")
public class client extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public client() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		user u=(user) request.getSession().getAttribute("user");
		
		if(u==null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}else {
			
			
			crudFacture crdf=new crudFacture();
			facture f=crdf.findFactureUser(u.getId());
			request.getSession().setAttribute("facture",f);
			crudAbonnement crdab=new crudAbonnement();
			request.getSession().setAttribute("abonnement", crdab.findAbonnement(f.getId_abonnement()));
			
			facture facture_client=(facture) request.getSession().getAttribute("facture");
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern( "uuuu-MM-dd" ) ;
			LocalDate last_date = LocalDate.parse( facture_client.getDate_debut().toString() , format ) ;
			LocalDate next_date=last_date.plusMonths(1);
			LocalDate today=LocalDate.now();
			
			boolean should_pay;
			

			if(next_date.isBefore(today)) {
				crdf.updateFacture(facture_client.getId(), facture_client.getId_user(), facture_client.getId_abonnement(), 0,facture_client.getDate_debut());
				should_pay=true;
			}else {
				
				should_pay=false;
			}
			
		    
			
			
			
			
			request.setAttribute("last_date", last_date.toString());
			request.setAttribute("next_date", next_date.toString());
			request.setAttribute("should_pay", should_pay);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request, response);

		}
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String num_carte=request.getParameter("num_carte");
		String date_end_str=request.getParameter("date_end");
		String last_num_carte=request.getParameter("last_num_carte");
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd" ) ;
		LocalDate date_end = LocalDate.parse( date_end_str , f ) ;
		abonnement abonnement_client=(abonnement) request.getSession().getAttribute("abonnement");
		
		if(banqueService.pay(num_carte,date_end,last_num_carte,abonnement_client.getPrice())) { //varification service de bancaire si la date d'expiration est apres de la date d'aujourdui et le num de care contient 16 chiffres
			
			crudFacture crdf=new crudFacture();
			facture facture_client=(facture) request.getSession().getAttribute("facture");			
			user client=(user) request.getSession().getAttribute("user");
			

			try {
				crdf.updateFacture(facture_client.getId(), facture_client.getId_user(), facture_client.getId_abonnement(), 1,new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("num_carte", num_carte);
			
			try {
				JavaMailUtil.sendMail(client.getEmail(), client, facture_client, abonnement_client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/client/succe.jsp").forward(request, response);
		


			
		}else {
			response.sendRedirect(request.getContextPath()+"/client?msg=failed");
		}
		
		
		
		
	}
	
	
	

}
