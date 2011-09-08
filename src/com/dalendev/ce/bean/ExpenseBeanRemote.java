package com.dalendev.ce.bean;

import java.util.Date;

import javax.ejb.Remote;

import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.User;

@Remote
public interface ExpenseBeanRemote
{
	public Expense createExpense(String name, String description, Date date, int project, User meta_user);
	public Boolean addUserToExpense(int user, int expense, Double amount, User meta_user);
}
