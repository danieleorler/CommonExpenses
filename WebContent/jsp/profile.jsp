<%@page import="com.dalendev.ce.table.User" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container title="Your Profile">

	<jsp:body>
		<h3>Hi ${sessionScope.loggedUser.username}</h3>
		<p>these are your data:</p>
		<t:user_details user="${sessionScope.loggedUser}" />
		<p>use the box on the right side of the page to show your projects</p>
	</jsp:body>
	
</t:container>