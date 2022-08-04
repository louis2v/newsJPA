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
 * Servlet implementation class Subscribe
 */
@WebServlet("/subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/subscribe.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscribe() {
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
		// TODO Auto-generated method stub
		String action = request.getParameter("act");
		if(action.equals("add")) {
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");
			this.addUser(pseudo,password);
		}
		doGet(request, response);
	}

	private void addUser(String pseudo,String password) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Boolean transac = false;
		String mdp = null;

		try {
			mdp = HashText.sha1(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}finally {
			if(mdp.equals(null)) {
				em.getTransaction().rollback();
			}else {
				User user = new User(pseudo, mdp);
				em.getTransaction().begin();

				try {
					em.persist(user);
					transac = true;
				}finally {
					if(transac) {
						em.getTransaction().commit();
					}else {
						em.getTransaction().rollback();
					}
				}
			}
		}

		em.close();
	}

}
