<%@tag language="java" description="Overall Page Template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" %>
<html>
	<body>
		<!-- HEADER  -->
		<jsp:include page="/jsp/blocks/header.jsp" />

		<!-- HEAD -->
		<body>
			<div id="main">
				<div id="header">
					<div id="logo">
						<h1><a href="/ce/">Common Expenses <span class="version">0.1</span></a></h1>
						<div class="slogan">Track your Expenses</div>
					</div>
				</div>
				
		<!-- CONTAINER -->
		<div id="site_content">
			<div>
				<!-- SIDEBAR -->
				<jsp:include page="/jsp/blocks/sidebar.jsp" />
			</div>
			<div id="content">
				<!-- CONTENT -->
				<h1>${title}</h1>
				<jsp:doBody/>			
			</div>
		</div>

		<!-- FOOTER -->
		<div id="pagefooter">
			<jsp:include page="/jsp/blocks/footer.jsp" />
		</div>
	</body>
</html>

