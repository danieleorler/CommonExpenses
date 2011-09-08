<%@page import="com.dalendev.ce.table.User" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container title="Welcome on Board">

	<jsp:body>
		<h3>Congratulation ${newuser.username}</h3>
		<p>you are now registered to Common Expenses, here are your data:</p>
		<t:user_details user="${newuser}" />
		<p>use the box on the right side of the page to log-in and start tracking</p>
	</jsp:body>
	
</t:container>
