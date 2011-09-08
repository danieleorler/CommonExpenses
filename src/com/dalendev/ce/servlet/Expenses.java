package com.dalendev.ce.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dalendev.ce.table.User;

/**
 * Servlet implementation class Expenses
 */
@WebServlet("/Expenses")
public class Expenses extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@EJB
	private com.dalendev.ce.bean.ExpenseBeanRemote expense;
	@EJB
	private com.dalendev.ce.bean.ProjectBeanRemote project;	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Expenses()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		route(request,response);
	}
	
	public void route(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		
	    Logger logger = LoggerFactory.getLogger(Project.class);
	    logger.info("action executed: "+action);

		
		if("addshare".compareToIgnoreCase(action) == 0)
		{
			addShare(request, response);
		}
		else if ("create".compareToIgnoreCase(action) == 0)
		{
			createExpense(request, response);
		}
		else if ("addexpense".compareToIgnoreCase(action) == 0)
		{
			newExpense(request, response);
		}		
		else
		{
			request.setAttribute("error", "Page not Found");
			request.setAttribute("description", "The page you were looking for is not available");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);			
		}		
	}
	
	private void createExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("ename");
		String description = request.getParameter("edesc");
		String sdate = request.getParameter("edate");
		
		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
		Date date;
		try
		{
			date = formatter.parse(sdate);
			
			int pid = Integer.parseInt(request.getParameter("epid"));
			User user = (User)request.getSession().getAttribute("loggedUser");
			
			com.dalendev.ce.table.Expense e = expense.createExpense(name, description, date, pid,user);
			
			request.setAttribute("message", "Expense Saved");
			request.setAttribute("description", "The expense <i>"+e.getName()+"</i> has been succesfully created!");
			request.getRequestDispatcher("/jsp/messages/ok.jsp").forward(request, response);			
		}
		catch (ParseException e1)
		{
			request.setAttribute("error", "Expense not saved");
			request.setAttribute("description", "The date you provided is not valid");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);	
		}
	}
	
	private void addShare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int uid = Integer.parseInt(request.getParameter("uid"));
		int eid = Integer.parseInt(request.getParameter("eid"));
		Double amount = Double.parseDouble(request.getParameter("amount"));
		User user = (User)request.getSession().getAttribute("loggedUser");
		
		if(expense.addUserToExpense(uid, eid, amount, user))
		{
			request.setAttribute("message", "Share added");
			request.setAttribute("description", "You have added a share");			
			request.getRequestDispatcher("/jsp/messages/ok.jsp").forward(request, response);				
		}
		else
		{
			request.setAttribute("error", "Share not added");
			request.setAttribute("description", "There were some problems adding the share");				
			request.getRequestDispatcher("/jsp/messages/error.jsp").forward(request, response);			
		}
	}
	
	private void newExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int pid = Integer.parseInt(request.getParameter("pid"));
//		request.setAttribute("users", project.listUsers(pid));
		request.setAttribute("pid", pid);
		request.getRequestDispatcher("/jsp/expense/newexpense.jsp").forward(request, response);	
	}

}
