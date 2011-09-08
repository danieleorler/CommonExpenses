<%@page import="com.dalendev.ce.util.WebConfig"%>

<div class="sidebar">
	<h3>Hi ${sessionScope.loggedUser.username}!</h3>
	<p>Here is your menu:</p>
	
	<ul>
		<li><a href="/ce/s/project?action=list">Your Projects</a></li>
		<li><a>Your Profile</a></li>
		<li><a href="/ce/s/authenticate">Log-Out</a></li>
	</ul>
</div>