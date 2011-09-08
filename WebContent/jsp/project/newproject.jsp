<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container title="New Project">
	
	<jsp:body>
		<form action="/ce/s/project" method="POST" id="subscribe">
		
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="text" name="pname" value="Project Name" />
			</p>
			<p style="padding: 0 0 9px 0;">
				<textarea name="pdesc" class="search">Project Description</textarea>
			</p>
			
			<input type="hidden" name="action" value="create" />
							
			<p><input class="subscribe" name="subscribe" type="submit" value="Save" /></p>		
		</form>
	</jsp:body>
	
</t:container>
