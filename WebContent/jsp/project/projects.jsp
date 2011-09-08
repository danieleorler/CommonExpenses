<%@page import="com.dalendev.ce.table.User" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:container title="Projects Management">

	<jsp:body>
	
		<h3>Projects you lead</h3>
		
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Created at</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${managed}">
					<tr>
						<td>${project.name}</td>
						<td>${project.description}</td>
						<td>${project.meta_ts}</td>
						<td>edit</td>
						<td>delete</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="/ce/newproject">Create new Project</a>

		<h3>Projects you have joined</h3>

		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Created at</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${partecipated}">
					<tr>
						<td><a href='/ce/s/project?pid=<c:out value="${project.id}" />&action=show'>${project.name}</a></td>
						<td>${project.description}</td>
						<td>${project.meta_ts}</td>
						<td>
							<form action="/ce/s/expense" method="POST">
								<input type="hidden" name="pid" value='<c:out value="${project.id}" />' />
								<input type="hidden" name="action" value="addexpense" />
								<input type="submit" value="add expense" />
							</form>
						</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h3>Projects you could join</h3>		
		
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Created at</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${join}">
					<tr>
						<td>${project.name}</td>
						<td>${project.description}</td>
						<td>${project.meta_ts}</td>
						<td>
							<form action="/ce/s/project" method="POST">
								<input type="hidden" name="pid" value='<c:out value="${project.id}" />' />
								<input type="hidden" name="action" value="join" />
								<input type="submit" value="join" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>				
		
	</jsp:body>
	
</t:container>
