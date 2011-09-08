package com.dalendev.ce.table;

import javax.persistence.ManyToOne;

public class PartecipateId implements java.io.Serializable
{
	@ManyToOne
	private User user;
	@ManyToOne
	private Project project;
	
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
	
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
        
		if(o == null || getClass() != o.getClass())
			return false;
 
		PartecipateId that = (PartecipateId) o;
 
		if(user != null ? !user.equals(that.user) : that.user != null)
			return false;
		
		if(project != null ? !project.equals(that.project) : that.project != null)
			return false;
 
        return true;
    }
 
	public int hashCode()
	{
		int result;
		result = (user != null ? user.hashCode() : 0);
		result = 31 * result + (project != null ? project.hashCode() : 0);
		return result;
	}
	
}
