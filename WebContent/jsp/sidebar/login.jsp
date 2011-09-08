<%@page import="com.dalendev.ce.util.WebConfig"%>

<div class="sidebar">

	<h3>Log-In</h3>
	<p>Before you can use Common Expenses you need to log-in.</p>
	
	<form method="post" action="/ce/s/authenticate" id="subscribe">
		<p style="padding: 0 0 9px 0;">
			<input class="search" type="text" name="username" value="Username" />
		</p>
		<p style="padding: 0 0 9px 0;">
			<input class="search" type="password" name="password" value="Password" />
		</p>		
		<p><input class="subscribe" name="subscribe" type="submit" value="Log-In" /></p>
	</form>
	
	<a href="/ce/register">Register</a>
	
</div>