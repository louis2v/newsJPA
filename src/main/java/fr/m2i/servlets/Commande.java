package fr.m2i.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.Commandes;

/**
 * Servlet implementation class Commande
 */
@WebServlet("/commande")
public class Commande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/commande.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Commandes commande = em.find(Commandes.class, 1);

		List<Commandes> commande2 = em.createQuery("select commandes from Commandes commandes join Clients id_client on commandes.id_client._id = id_client._id where id_client._id = 1", Commandes.class).getResultList();

		//List<Commandes> commande3 = em.createQuery("select c from Commandes c join c.id_client clients where clients._id = 1", Commandes.class).getResultList();

		request.setAttribute("commande", commande);
		request.setAttribute("commande2", commande2);
		//request.setAttribute("commande3", commande3);

		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
