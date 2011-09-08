<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:container title="Project Details">
	
	<jsp:body>
	
		<div id="project">
			<h3>${project.name}</h3>
			<p>${project.description}</p>
		</div>
		
		<script type="text/javascript">
			var users = ${json};
		</script>
		
		<div id="expenses">
			<c:forEach var="expense" items="${expenses}">
			
				<div class="expense">
					<h4>${expense.e.name}</h4>
					<span>${expense.e.description}</span>
				</div>
				
				<table id='<c:out value="${expense.e.id}" />'>
					<thead>
						<tr>
							<th width="40%">User</th>
							<th width="40%">Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="share" items="${expense.s}">
							<tr>
								<td>${share.user.username}</td>
								<td>${share.amount}</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a class="add">add share</a>
				<br class="clear">
			</c:forEach>
		</div>
		
	</jsp:body>
	
</t:container>
