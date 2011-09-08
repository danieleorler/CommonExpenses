<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:container title="New Expense">
	
	<jsp:body>
		<form action="/ce/s/expense" method="POST" id="subscribe">
		
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="text" name="ename" value="Expense Name" />
			</p>
			<p style="padding: 0 0 9px 0;">
				<textarea name="edesc" class="search">Expense Description</textarea>
			</p>
			<p style="padding: 0 0 9px 0;">
				<input style="width: 300px;" class="search" type="date" name="edate" value="" />
			</p>			
			
			<input type="hidden" name="epid" value='<c:out value="${pid}" />' />
			
			<input type="hidden" name="action" value="create" />
							
			<p><input class="subscribe" name="subscribe" type="submit" value="Save" /></p>		
		</form>
	</jsp:body>
	
</t:container>
