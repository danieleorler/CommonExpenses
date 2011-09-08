package com.dalendev.ce.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dalendev.ce.table.User;
import com.dalendev.ce.util.WebConfig;

/**
 * Servlet implementation class Auhenication
 */
@WebServlet("/Auhenication")
public class Authentication extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private com.dalendev.ce.bean.AuthenticationBeanRemote authentication;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		authentication.logout();
		request.getSession().removeAttribute("loggedUser");
		response.sendRedirect("/"+WebConfig.APP_NAME+"/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		User u = authentication.login(request.getParameter("username"), request.getParameter("password"));
		
		if(u != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("loggedUser", u);
			response.sendRedirect("/"+WebConfig.APP_NAME+"/");
		}
		else
		{
			request.setAttribute("error", "Login Failed");
			request.setAttribute("description", "check your username and password");
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);
		}
	}

}
