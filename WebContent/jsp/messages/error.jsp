<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container title="An Error Occoured">
	
	<jsp:body>
		<h2>${error}</h2>
		<p>${description}</p>
	</jsp:body>
	
</t:container>