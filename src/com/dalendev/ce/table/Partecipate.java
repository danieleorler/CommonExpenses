package com.dalendev.ce.table;

import java.util.Date;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
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
import javax.persistence.Transient;

@Entity
@Table(name = "partecipate")
@AssociationOverrides
({
	@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user")),
	@AssociationOverride(name = "pk.project", joinColumns = @JoinColumn(name = "project"))
})
public class Partecipate implements java.io.Serializable
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Embedded
	private PartecipateId pk = new PartecipateId();	
	
	@Column(name = "leader")
	private Boolean leader;
	
	@Temporal(TemporalType.DATE)
	@Column(name="meta_ts", nullable=false)
	private Date meta_ts = new Date();	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meta_user", referencedColumnName="id", nullable = false)
	private User meta_user;

	public Partecipate()
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
	 * @return the pk
	 */
	public PartecipateId getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(PartecipateId pk) {
		this.pk = pk;
	}
	
	@Transient
	public User getUser()
	{
		return getPk().getUser();
	}
 
	public void setUser(User user)
	{
		getPk().setUser(user);
	}
 
	@Transient
	public Project getProject()
	{
		return getPk().getProject();
	}
 
	public void setProject(Project project)
	{
		getPk().setProject(project);
	}	

	/**
	 * @return the leader
	 */
	public Boolean getLeader() {
		return leader;
	}

	/**
	 * @param leader the leader to set
	 */
	public void setLeader(Boolean leader) {
		this.leader = leader;
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
	
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		Partecipate that = (Partecipate) o;
 
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;
 
		return true;
	}
 
	public int hashCode()
	{
		return (getPk() != null ? getPk().hashCode() : 0);
	}	

}
