package com.dalendev.ce.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dalendev.ce.util.WebConfig;

/**
 * Servlet implementation class Bootstrap
 */
@WebServlet("/Bootstrap")
public class Bootstrap extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bootstrap()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		init(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		init(request, response);
	}
	
	public void init(HttpServletRequest rq,HttpServletResponse re) throws ServletException, IOException
	{
		HttpSession s = rq.getSession();
		
		s.setAttribute("app", WebConfig.APP_NAME);
		s.setAttribute("img", WebConfig.ASSETS_FOLDER+"img");
		s.setAttribute("css", WebConfig.ASSETS_FOLDER+"img");
		s.setAttribute("js", WebConfig.ASSETS_FOLDER+"img");
		re.sendRedirect("/"+WebConfig.APP_NAME+"/");
	}

}
