package myapp.internationalisation;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changeLangue
 */
@WebServlet("/changeLangue")
public class ChangeLangue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLangue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// I use this as a servlet because i need to update my Map table on the session that contain all words i use 
		
		International.setLangue(request.getParameter("langue").toString().toLowerCase().trim());
		Hashtable<String, String> map=International.toHashtable();
		
		
		request.getSession().setAttribute("r", map);
		
		// in login servlet if user are already log in he will be redirected directly to index client
		response.sendRedirect(request.getContextPath()+"/login");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
