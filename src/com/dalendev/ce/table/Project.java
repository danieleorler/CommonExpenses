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
@Table(name = "project")
public class Project implements java.io.Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "description", length = 4000)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="meta_ts", nullable=false)
	private Date meta_ts = new Date();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<Expense> expenses = new HashSet<Expense>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.project", cascade = CascadeType.ALL, targetEntity = Partecipate.class)
	private Set<Partecipate> partecipants = new HashSet<Partecipate>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meta_user", referencedColumnName="id", nullable = false)
	private User meta_user;
	
	public Project()
	{
	}

	public Project(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public Project(String name, String description, User meta_user)
	{
		super();
		this.name = name;
		this.description = description;
		this.meta_user = meta_user;
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
	 * @return the expenses
	 */
	public Set<Expense> getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	/**
	 * @return the partecipants
	 */
	public Set<Partecipate> getPartecipants() {
		return partecipants;
	}

	/**
	 * @param partecipants the partecipants to set
	 */
	public void setPartecipants(Set<Partecipate> partecipants) {
		this.partecipants = partecipants;
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
	
}
