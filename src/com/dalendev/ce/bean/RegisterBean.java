package com.dalendev.ce.bean;

import javax.ejb.Stateless;

import org.hibernate.Session;

import com.dalendev.ce.table.User;
import com.dalendev.ce.util.HibernateUtil;

/**
 * Session Bean implementation class RegisterBean
 */
@Stateless
public class RegisterBean implements RegisterBeanRemote
{

    /**
     * Default constructor. 
     */
    public RegisterBean()
    {
    }
    
	public User createUser(String username, String password, String email)
	{
		User user = new User(1, username, password, email);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		return user;
	}    

}
