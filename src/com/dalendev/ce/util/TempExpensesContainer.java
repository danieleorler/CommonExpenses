package com.dalendev.ce.util;

import java.util.Set;
import com.dalendev.ce.table.Expense;
import com.dalendev.ce.table.Share;

public class TempExpensesContainer implements java.io.Serializable
{
	private Expense e;
	private Set<Share> s;
	
	public TempExpensesContainer(){}

	/**
	 * @return the e
	 */
	public Expense getE()
	{
		return e;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(Expense e)
	{
		this.e = e;
	}

	/**
	 * @return the s
	 */
	public Set<Share> getS()
	{
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(Set<Share> s)
	{
		this.s = s;
	}
	
	
	
}
