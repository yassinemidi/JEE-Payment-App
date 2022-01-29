package myapp.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myapp.dao.UserDAO;
import myapp.dao.AbonnementDAO;
import myapp.dao.FactureDAO;
import myapp.mail.JavaMailUtil;
import myapp.models.abonnement;
import myapp.models.facture;
import myapp.models.user;

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
		
		
		//Get All users with their informations and simplify them with a Map table (list_abonnees)
		UserDAO at=new UserDAO();
		FactureDAO crdf=new FactureDAO();
		AbonnementDAO crdab=new AbonnementDAO();
		
		ArrayList<Hashtable<String, String>> list_abonnees=new ArrayList<Hashtable<String,String>>();
		Hashtable<String, String> tmp ;
		
		abonnement tmp_ab;
		facture tmp_fac;
		
		
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
		
		
		//Send the Map table with request to index.jsp
		request.setAttribute("list_abonnees",list_abonnees);
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/agent/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		FactureDAO crdf=new FactureDAO();
		UserDAO at=new UserDAO();
		AbonnementDAO crdab=new AbonnementDAO();
		
		//Change state of facture to paid in app database
		crdf.payerFacture(Integer.parseInt( request.getParameter("id_facture")));
	
		
		
		
		
		facture tmp_fac=crdf.findFacture(Integer.parseInt( request.getParameter("id_facture")));
		abonnement tmp_ab=crdab.findAbonnement(tmp_fac.getId_abonnement());
		user tmp_user=at.findUser(tmp_fac.getId_user());
		
		//send recu data with request
		request.setAttribute("tmp_fac",tmp_fac);
		request.setAttribute("tmp_ab",tmp_ab);
		request.setAttribute("tmp_user",tmp_user);
		request.setAttribute("today",LocalDate.now().toString());
		
		
		
		try {
			
			//Send successful mail to costumer
			JavaMailUtil.sendMail(tmp_user.getEmail(), tmp_user, tmp_fac, tmp_ab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/agent/recu.jsp").forward(request, response);

	}

}
