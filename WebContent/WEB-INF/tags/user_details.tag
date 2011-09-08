<%@tag description="User Details" pageEncoding="UTF-8"%>
<%@tag import="com.dalendev.ce.table.User" %>
<%@attribute name="user" required="true" type="com.dalendev.ce.table.User" %>

	<h4>Username:</h4> ${user.username} <br>
	<h4>Email:</h4> ${user.email} <br>
	<br>
