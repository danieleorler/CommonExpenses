<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container title="User Registration">
	
	<jsp:body>
		<form action="/ce/s/register" method="POST" id="subscribe">
		
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="text" name="username" value="Username" />
			</p>
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="password" name="password" value="Password" />
			</p>
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="text" name="email" value="Email" />
			</p>
							
			<p><input class="subscribe" name="subscribe" type="submit" value="Register" /></p>		
		</form>
	</jsp:body>
	
</t:container>
