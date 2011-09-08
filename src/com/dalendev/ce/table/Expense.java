package com.dalendev.ce.table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "expense")
public class Expense implements java.io.Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@Temporal(TemporalType.DATE)
	@Column(name="meta_ts", nullable=false)
	private Date meta_ts = new Date();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "expense", cascade = CascadeType.ALL)
	private Set<Share> shares = new HashSet<Share>(0);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project", referencedColumnName="id", nullable = false)
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meta_user", referencedColumnName="id", nullable = false)
	private User meta_user;

	public Expense()
	{
	}	
	
	public Expense(String name, String description, Date date, Project project)
	{
		this.name = name;
		this.description = description;
		this.date = date;
		this.project = project;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
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
	 * @return the shares
	 */
	public Set<Share> getShares() {
		return shares;
	}

	/**
	 * @param shares the shares to set
	 */
	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}
	
}
