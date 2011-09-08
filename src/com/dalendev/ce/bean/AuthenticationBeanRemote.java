package com.dalendev.ce.bean;

import javax.ejb.Remote;
import com.dalendev.ce.table.User;

@Remote
public interface AuthenticationBeanRemote
{
	public User login(String username, String password);
	public void logout();
}
