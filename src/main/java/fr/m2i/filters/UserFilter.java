package fr.m2i.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.m2i.servlets.Login;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/login")
public class UserFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public UserFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(httpRequest.getMethod().equalsIgnoreCase("POST")) {
			String pseudo = httpRequest.getParameter("pseudo");
			String password = httpRequest.getParameter("password");
			httpRequest.getSession().setAttribute("logged", Login.checkUser(pseudo,password));
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
