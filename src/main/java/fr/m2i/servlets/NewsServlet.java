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

import fr.m2i.models.Commentaires;
import fr.m2i.models.News;
import fr.m2i.models.User;

/**
 * Servlet implementation class News
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/news.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String theNews = request.getParameter("news");
		request.setAttribute("news", this.getOneNews(theNews));
		request.setAttribute("commentaires", this.getCommentaires(theNews));

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
			String comment = request.getParameter("comment");
			String idNews = request.getParameter("news");
			String userName = (String) request.getSession().getAttribute("user");
			this.addCommentaire(comment, idNews, userName);
		}
		doGet(request, response);
	}

	/*
	 * GET ONE NEWS
	 */
	private News getOneNews(String theNews) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		News news = em.find(News.class, Integer.parseInt(theNews));

		return news;
	}

	private List<Commentaires> getCommentaires(String theNews) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		List<Commentaires> commentaire = em.createQuery("SELECT commentaires FROM Commentaires commentaires WHERE commentaires.id_news ="+Integer.parseInt(theNews), Commentaires.class).getResultList();

		return commentaire;
	}

	private void addCommentaire(String comment,String idNews,String userName) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Boolean transac = false;

		News news = em.find(News.class, Integer.parseInt(idNews));

		User user = em.createQuery("SELECT user FROM User user WHERE user._pseudo LIKE :pseudo", User.class)
				.setParameter("pseudo", userName)
				.getSingleResult();

		Commentaires commentary = new Commentaires(comment, news, user);
		em.getTransaction().begin();

		try {
			em.persist(commentary);
			transac = true;
		}finally {
			if(transac) {
				em.getTransaction().commit();
			}else {
				em.getTransaction().rollback();
			}
		}
		em.close();


	}


}
