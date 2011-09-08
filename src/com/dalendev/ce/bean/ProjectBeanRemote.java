package com.dalendev.ce.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.Project;
import com.dalendev.ce.table.Share;
import com.dalendev.ce.table.User;
import com.dalendev.ce.util.TempExpensesContainer;

@Remote
public interface ProjectBeanRemote
{
	/*
	 * list all users projects
	 */
	public HashMap<String, List<Project>> list(User user);
	public Project createProject(String name, String description, User user);
	public Project joinProject(int project, User user);
	public List<User> listUsers(int project);
	public Project getProject(int project);
	public List<TempExpensesContainer> getProjectExpenses(int project);
	//public Set<Share> getExpenseShares(int share);
}
