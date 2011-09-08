package com.dalendev.ce.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.Partecipate;
import com.dalendev.ce.table.Project;
import com.dalendev.ce.table.Share;
import com.dalendev.ce.table.User;
import com.dalendev.ce.util.HibernateUtil;
import com.dalendev.ce.util.TempExpensesContainer;

@Stateless
public class ProjectBean implements ProjectBeanRemote
{

	private Session session;
	
	public ProjectBean()
	{
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@Override
	public HashMap<String, List<Project>> list(User user)
	{
		
		Query q = session.createQuery("FROM Partecipate WHERE user = :user");
		q.setParameter("user", user.getId());
		
		List<Partecipate> tmp = q.list();
		
		List<Project> managed = new ArrayList<Project>();
		List<Project> partecipated = new ArrayList<Project>();
		List<Project> join = new ArrayList<Project>();
		
		Iterator<Partecipate> i = tmp.iterator();
		
		while(i.hasNext())
		{
			Partecipate ptemp = i.next();
			if(ptemp.getLeader())
				managed.add(ptemp.getProject());
			
			partecipated.add(ptemp.getProject());
		}
		
		q = session.createQuery("FROM Partecipate AS p WHERE p.pk.project.id NOT IN (SELECT par.pk.project.id FROM Partecipate AS par WHERE par.pk.user.id = :user)");
		q.setParameter("user", user.getId());
		tmp = q.list();
		i = tmp.iterator();
		
		while(i.hasNext())
		{
			Partecipate ptemp = i.next();
			join.add(ptemp.getProject());
		}		
		
		HashMap<String, List<Project>> res = new HashMap<String, List<Project>>();
		
		res.put("managed",managed);
		res.put("partecipated", partecipated);
		res.put("join", join);
		
		return res;
	}

	@Override
	public Project createProject(String name, String description, User user)
	{
		session.getTransaction().begin();
		
		//project entity
		Project project = new Project(name, description, user);
		
		session.save(project);		
		
		//partecipate entity
		Partecipate partecipate = new Partecipate();
		//set user who partecipate
		partecipate.setUser(user);
		//set project to join
		partecipate.setProject(project);
		//set user as leader
		partecipate.setLeader(true);
		//set user metadata
		partecipate.setMeta_user(user);	
		session.save(partecipate);
		
		//add partecipation to user
		project.getPartecipants().add(partecipate);
		
		//session.save(user);
		session.getTransaction().commit();
		return project;
	}
	
	public Project joinProject(int project, User user)
	{
		
		Project p = (Project)session.get(Project.class, project);
		
		session.getTransaction().begin();
		
		Partecipate partecipate = new Partecipate();
		partecipate.setProject(p);
		partecipate.setLeader(false);
		partecipate.setUser(user);
		partecipate.setMeta_user(user);
		
		session.save(partecipate);
		
		session.getTransaction().commit();
		
		return p;
	}
	
	public List<User> listUsers(int project)
	{
		
		Project p = (Project)session.get(Project.class, project);
		
	    Logger logger = LoggerFactory.getLogger(Project.class);
	    logger.info("fetching user for project: "+p.getName()+" id: "+p.getId());		
		List<User> list = new ArrayList<User>();
		
		for(Partecipate par : p.getPartecipants())
		{
			list.add(par.getUser());
		}
		
		return list;
	}
	
	public Project getProject(int project)
	{
		return (Project)session.get(Project.class, project);
	}
	
	public List<TempExpensesContainer> getProjectExpenses(int project)
	{
		Query q = session.createQuery("FROM Expense WHERE project = "+project);
		
		List<Expense> tmp = q.list();
		Iterator<Expense> i = tmp.iterator();
		
		List<TempExpensesContainer> ret = new ArrayList<TempExpensesContainer>();
		
		while(i.hasNext())
		{
			TempExpensesContainer t = new TempExpensesContainer();
			Expense e = i.next();
			t.setE(e);
			
			q = session.createQuery("FROM Share WHERE expense = "+t.getE().getId());
			
			List<Share> shares = q.list();
			Set<Share> toReturn = new HashSet<Share>(0);
			Iterator<Share> j = shares.iterator();
			while(j.hasNext())
			{
				Share s = j.next();
				toReturn.add(s);
			}
			
			t.setS(toReturn);
			ret.add(t);
		}
		
		return ret;
	}
	
	public Project updateProject(int project, String name, String description)
	{
		Project p = (Project)session.get(Project.class, project);
		
		p.setName(name);
		p.setDescription(description);
		
		session.getTransaction().begin();
		session.update(p);
		session.getTransaction().commit();
		
		return p;
		
	}
	
	public Boolean deleteProject(int project)
	{
		session.getTransaction().begin();
		Project p = (Project)session.get(Project.class, project);
		
	    Logger logger = LoggerFactory.getLogger(Project.class);
	    logger.info("deleting project: "+p.getName()+" id: "+p.getId());		
		
		session.delete(p);
		session.getTransaction().commit();
		return true;
	}

}
