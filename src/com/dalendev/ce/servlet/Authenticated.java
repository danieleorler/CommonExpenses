package com.dalendev.ce.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dalendev.ce.table.Project;
import com.dalendev.ce.table.User;

/**
 * Servlet Filter implementation class Authenticated
 */
@WebFilter("/Authenticated")
public class Authenticated implements Filter {

    /**
     * Default constructor. 
     */
    public Authenticated() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		Logger logger = LoggerFactory.getLogger(Authenticated.class);
		
		//check if user can access the page requested
		if(request instanceof HttpServletRequest)
		{
			String url = ((HttpServletRequest)request).getRequestURL().toString();
			url = url.substring(26, url.length());
			
			//if it is not an asset request
			if(url.indexOf('.') < 0)
			{
				logger.info("filtering URL: "+url);
				
				HttpSession session = ((HttpServletRequest)request).getSession();
				User user = (User)session.getAttribute("loggedUser");
				
				//these pages can be accessed without authentication
				Boolean noAuth = url.equalsIgnoreCase("/ce")
								|| url.equalsIgnoreCase("/ce/")
								|| url.equalsIgnoreCase("/ce/register")
								|| url.equalsIgnoreCase("/ce/s/authenticate");
				
				if(user == null && !noAuth)
				{
					((HttpServletRequest)request).setAttribute("error", "User not Authenticated");
					((HttpServletRequest)request).setAttribute("description", "You need to log-in to access this page!");
					((HttpServletRequest)request).getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);
					logger.info("user not logged tried to access: "+url);
				}
				else
				{
					chain.doFilter(request, response);
				}
			}
			else
				chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
	    Logger logger = LoggerFactory.getLogger(Authenticated.class);
	    logger.info("filtering up");
	}

}
