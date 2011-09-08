package com.dalendev.ce.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.User;
import com.dalendev.ce.util.TempExpensesContainer;
import com.google.gson.Gson;

/**
 * Servlet implementation class Project
 */
@WebServlet("/Project")
public class Project extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@EJB
	private com.dalendev.ce.bean.ProjectBeanRemote project; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		routeGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		route(request, response);
	}

	
	private void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("pname");
		String description = request.getParameter("pdesc");
		User user = (User)request.getSession().getAttribute("loggedUser");
		com.dalendev.ce.table.Project p = project.createProject(name, description, user);
		
		request.setAttribute("message", "Project Saved");
		request.setAttribute("description", "The project <i>"+p.getName()+"</i> has been succesfully created!");
		request.getRequestDispatcher("/jsp/messages/ok.jsp").forward(request, response);
	}
	
	private void joinProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		com.dalendev.ce.table.Project result = project.joinProject(pid, (User)request.getSession().getAttribute("loggedUser"));
		
		if(result != null)
		{
			request.setAttribute("message", "Project Joined");
			request.setAttribute("description", "You have succesfully joined the project <i>"+result.getName()+"</i>");			
			request.getRequestDispatcher("/jsp/messages/ok.jsp").forward(request, response);			
		}
		else
		{
			request.setAttribute("error", "Project not Joined");
			request.setAttribute("description", "There were some problems joining the project");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);
		}
	}
	
	private void listProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<String, List<com.dalendev.ce.table.Project>> map = project.list((User)request.getSession().getAttribute("loggedUser"));
		
		request.setAttribute("managed", map.get("managed"));
		request.setAttribute("partecipated", map.get("partecipated"));
		request.setAttribute("join", map.get("join"));
		
		request.getRequestDispatcher("/jsp/project/projects.jsp").forward(request, response);
	}
	
	private void showProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		com.dalendev.ce.table.Project p = project.getProject(pid);
		List<User> users = project.listUsers(pid);
		//users to JSON
		Gson gson = new Gson();
		String json = gson.toJson(users);
		
		//retireve project's data
		List<TempExpensesContainer> expenses = project.getProjectExpenses(pid);
		
		request.setAttribute("json", json);
		request.setAttribute("project", p);
		request.setAttribute("expenses", expenses);
		request.getRequestDispatcher("/jsp/project/showproject.jsp").forward(request, response);
	}
	
	public void route(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		
	    Logger logger = LoggerFactory.getLogger(Project.class);
	    logger.info("action executed: "+action);

		
		if("join".compareToIgnoreCase(action) == 0)
		{
			joinProject(request, response);
		}
		else if ("create".compareToIgnoreCase(action) == 0)
		{
			createProject(request, response);
		}
		else
		{
			request.setAttribute("error", "Page not Found");
			request.setAttribute("description", "The page you were looking for is not available");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);			
		}		
	}
	
	public void routeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		
	    Logger logger = LoggerFactory.getLogger(Project.class);
	    logger.info("action executed: "+action);

		
		if("list".compareToIgnoreCase(action) == 0)
		{
			listProjects(request, response);
		}
		else if ("show".compareToIgnoreCase(action) == 0)
		{
			showProject(request, response);
		}
		else
		{
			request.setAttribute("error", "Page not Found");
			request.setAttribute("description", "The page you were looking for is not available");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);			
		}		
	}	
	
}
