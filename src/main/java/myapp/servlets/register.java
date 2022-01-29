package myapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myapp.dao.UserDAO;
import myapp.dao.AbonnementDAO;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AbonnementDAO cab=new AbonnementDAO();
		request.setAttribute("list_abn", cab.getData());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int id_abonnement=Integer.parseInt(request.getParameter("abonnement"));
		
		UserDAO at=new UserDAO();
		at.register(last_name, first_name, email, password, id_abonnement);

		response.sendRedirect(request.getContextPath()+ "/login" );
	}

}
