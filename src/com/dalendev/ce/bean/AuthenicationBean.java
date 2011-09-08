package com.dalendev.ce.bean;

import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;
import com.dalendev.ce.table.User;
import com.dalendev.ce.util.HibernateUtil;

@Stateless
public class AuthenicationBean implements AuthenticationBeanRemote
{

	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	public AuthenicationBean()
	{
	}

	public User login(String username, String password)
	{
		Query q = session.createQuery("FROM User WHERE username = :username AND password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		List tmp = q.list();
		
		if(tmp.size() == 1)
			return (User)tmp.get(0);
		else
			return null;
	}
	
	public void logout()
	{
		
	}
	
}
