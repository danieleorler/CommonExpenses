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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "user")
public class User implements java.io.Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "username", unique = true, nullable = false, length = 255)
	private String username;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="meta_ts", nullable=false)
	private Date meta_ts = new Date();
	
	@Column(name = "meta_user", nullable = false)
	private int meta_user;
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meta_user")
	private Set<Expense> created_expenses = new HashSet<Expense>(0);
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Share> shares = new HashSet<Share>(0);	
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meta_user")
	private Set<Project> projects = new HashSet<Project>(0);
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
	private Set<Partecipate> partecipations = new HashSet<Partecipate>(0);
	
	public User()
	{
	}

	public User(int id, String username, String password, String email)
	{
		this.setId(id);
		this.username = username;
		this.password = password;
		this.email = email;
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
	public void setId(int id)
	{
		this.id = id;
		this.setMeta_user(id);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	public int getMeta_user() {
		return meta_user;
	}

	/**
	 * @param meta_user the meta_user to set
	 */
	public void setMeta_user(int meta_user) {
		this.meta_user = meta_user;
	}
	
	/**
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the created_expenses
	 */
	public Set<Expense> getCreated_expenses() {
		return created_expenses;
	}

	/**
	 * @param created_expenses the created_expenses to set
	 */
	public void setCreated_expenses(Set<Expense> created_expenses) {
		this.created_expenses = created_expenses;
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

	/**
	 * @return the partecipations
	 */
	public Set<Partecipate> getPartecipations() {
		return partecipations;
	}

	/**
	 * @param partecipations the partecipations to set
	 */
	public void setPartecipations(Set<Partecipate> partecipations) {
		this.partecipations = partecipations;
	}	
	
}
