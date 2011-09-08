package com.dalendev.ce.bean;
import javax.ejb.Remote;

import com.dalendev.ce.table.User;

@Remote
public interface RegisterBeanRemote
{
	public User createUser(String username, String Password, String email);
}
