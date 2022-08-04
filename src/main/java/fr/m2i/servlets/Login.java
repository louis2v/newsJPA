package fr.m2i.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.User;
import fr.m2i.utiles.HashText;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/login.jsp";
	private static final String ADMIN="/WEB-INF/pages/admin.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		Boolean isok = this.checkUser(pseudo,password);
		if(isok) {
			User.isConnected = true;
			this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);
		}else {
			doGet(request, response);
		}
	}

	@SuppressWarnings("finally")
	private Boolean checkUser(String pseudo,String password) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Boolean transac = false;
		String mdp = null;

		//User user = em.find(User.class, pseudo);
		try {
			User user = (User) em.createNativeQuery("select * from user where pseudo = :nam ", User.class)
					.setParameter("nam", pseudo)
					.getSingleResult();

			if(user != null) {
				try {
					mdp = HashText.sha1(password);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}finally {
					if(mdp.equals(user.get_password())){
						return true;
					}else {
						return false;
					}
				}
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}


}
