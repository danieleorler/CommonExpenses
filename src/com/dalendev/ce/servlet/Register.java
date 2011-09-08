package com.dalendev.ce.servlet;
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dalendev.ce.table.User;

public class Register extends HttpServlet
{
   @EJB
   private com.dalendev.ce.bean.RegisterBeanRemote register;
  
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       String email = request.getParameter("email");
       
       User user = register.createUser(username, password, email);
       
       request.setAttribute("newuser", user);
       
       request.getRequestDispatcher("/jsp/registered.jsp").forward(request, response);  
   }
  
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
       processRequest(request, response);
   }
  
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
       processRequest(request, response);
   }
}