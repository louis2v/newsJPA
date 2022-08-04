package fr.m2i.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.News;
import fr.m2i.models.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/admin.jsp";
	private static final String LOGIN="/WEB-INF/pages/login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(User.isConnected) {
			request.setAttribute("news", Home.getAllNews());
			this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("act");
		if(action.equals("add")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String img = request.getParameter("img");
			this.addNews(title,description,img);
		}else if(action.equals("edit")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String img = request.getParameter("img");
			String id = request.getParameter("id");
			this.editNews(id, title, description, img);
		}else if(action.equals("delete")) {
			String id = request.getParameter("id");
			this.deteteNews(id);
		}


		doGet(request, response);
	}

	/*
	 * Add news
	 */
	private void addNews(String title,String description,String img) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Boolean transac = false;

		News news = new News(title, description,img);
		em.getTransaction().begin();

		try {
			em.persist(news);
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

	private void deteteNews(String id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Boolean transac = false;

		News news = em.find(News.class, Integer.parseInt(id));

		em.getTransaction().begin();
		try {
			em.remove(news);
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

	private void editNews(String id,String title,String description,String img) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		News news = em.find(News.class , Integer.parseInt(id));
		news.set_title(title);
		news.set_description(description);
		news.set_img(img);

		em.getTransaction().begin();
		boolean transactionOk = false;

		try {em.persist(news);
		transactionOk = true;
		}
		finally {
			if(transactionOk) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
}
