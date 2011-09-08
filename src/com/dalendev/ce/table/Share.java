package com.dalendev.ce.table;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "share")
public class Share implements java.io.Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "amount")
	private Double amount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="meta_ts", nullable=false)
	private Date meta_ts = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meta_user", referencedColumnName="id", nullable = false)
	private User meta_user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense", referencedColumnName="id", nullable = false)
	private Expense expense;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user", referencedColumnName="id", nullable = false)
	private User user;	
	
	public Share()
	{
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the meta_ts
	 */
	public Date getMeta_ts() {
		return meta_ts;
	}

	/**
	 * @param meta_ts the meta_ts to set
	 */
	public void setMeta_ts(Date meta_ts) {
		this.meta_ts = meta_ts;
	}

	/**
	 * @return the meta_user
	 */
	public User getMeta_user() {
		return meta_user;
	}

	/**
	 * @param meta_user the meta_user to set
	 */
	public void setMeta_user(User meta_user) {
		this.meta_user = meta_user;
	}

	/**
	 * @return the expense
	 */
	public Expense getExpense() {
		return expense;
	}

	/**
	 * @param expense the expense to set
	 */
	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
