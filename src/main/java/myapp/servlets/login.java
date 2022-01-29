package myapp.servlets;

import java.io.IOException;

import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myapp.dao.UserDAO;
import myapp.internationalisation.International;
import myapp.models.user;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
		
		Hashtable<String, String> map=International.toHashtable();
		request.getSession().setAttribute("r", map);
		
		
		user u=(user) request.getSession().getAttribute("user");
		if(u==null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);

		}else {
			if(u.getType_user()==1) response.sendRedirect(request.getContextPath()+"/client");

			if(u.getType_user()==0) response.sendRedirect(request.getContextPath()+"/agent");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDAO at=new UserDAO();
		user u=at.login(email, password);
		
		if( u != null) {
			request.getSession().setAttribute("user", u);
			
			
			if(u.getType_user()==0) {
				
				response.sendRedirect(request.getContextPath()+"/agent");
			}
			if(u.getType_user()==1) {
				
				response.sendRedirect(request.getContextPath()+"/client");
			}
			
			
			
		}else {
			// Si les données sont incorrect,l'utilisateur sera redirigé vers la page login 
			doGet(request, response);
		}
		

	}

}
