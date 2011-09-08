<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:container title="New Project">
	
	<jsp:body>
		<form action="/ce/s/project" method="POST" id="subscribe">
			
			<c:if test="${empty requestScope.project}" >
				<p style="padding: 0 0 9px 0;">
					<input style="width: 300px;" class="search" type="text" name="pname" value="Project Name" />
				</p>
				<p style="padding: 0 0 9px 0;">
					<textarea name="pdesc" class="search">Project Description</textarea>
				</p>
				<input type="hidden" name="action" value="create" />
			</c:if>
			
			<c:if test="${not empty requestScope.project}" >
				<p style="padding: 0 0 9px 0;">
					<input style="width: 300px;" class="search" type="text" name="pname" value='${requestScope.project.name}' />
				</p>
				<p style="padding: 0 0 9px 0;">
					<textarea name="pdesc" class="search">${requestScope.project.description}</textarea>
				</p>
				<input type="hidden" name="action" value="update" />
				<input type="hidden" name="pid" value="${requestScope.project.id}" />
			</c:if>
							
			<p><input class="subscribe" name="subscribe" type="submit" value="Save" /></p>		
		</form>
	</jsp:body>
	
</t:container>
