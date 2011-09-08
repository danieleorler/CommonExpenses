package com.dalendev.ce.bean;

import java.util.Date;

import javax.ejb.Stateless;

import org.hibernate.Session;

import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.Partecipate;
import com.dalendev.ce.table.Project;
import com.dalendev.ce.table.Share;
import com.dalendev.ce.table.User;
import com.dalendev.ce.util.HibernateUtil;

@Stateless
public class ExpenseBean implements ExpenseBeanRemote
{
	
	private Session session;
	
	public ExpenseBean()
	{
		session = HibernateUtil.getSessionFactory().openSession();
	}	

	@Override
	public Expense createExpense(String name, String description, Date date, int project, User meta_user)
	{
		Project p = (Project)session.get(Project.class, project);

		session.getTransaction().begin();
		
		//expense entity
		Expense expense = new Expense(name, description, date, p);
		expense.setMeta_user(meta_user);
		session.save(expense);
		
		p.getExpenses().add(expense);
		
		session.getTransaction().commit();
		
		return expense;
	}

	@Override
	public Boolean addUserToExpense(int user, int expense, Double amount, User meta_user)
	{
		Expense e = (Expense)session.get(Expense.class, expense);
		Project p = e.getProject();
		User u = (User)session.get(User.class, user);
		Boolean is_partecipating = false;
		
		//check if user is partecipating to the project
		for(Partecipate par : p.getPartecipants())
		{
			if(u.getId() == par.getUser().getId())
			{
				is_partecipating = true;
				break;
			}
		}
		
		if(is_partecipating)
		{
			session.getTransaction().begin();
			
			Share share = new Share();
			share.setAmount(amount);
			share.setExpense(e);
			share.setUser(u);
			share.setMeta_user(meta_user);
			
			session.save(share);
			
			session.getTransaction().commit();
			
			return true;
		}
		else
			return false;
		
	}

}
