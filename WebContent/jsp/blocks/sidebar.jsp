<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

			<div id="sidebar_container">

				<img class="paperclip" src=<t:assetspath file="paperclip.png" type="img" /> alt="paperclip" />
				<c:if test="${empty sessionScope.loggedUser}" >
					<%@include file="/jsp/sidebar/login.jsp" %>
				</c:if>
				<c:if test="${not empty sessionScope.loggedUser}" >
					<%@include file="/jsp/sidebar/user_panel.jsp" %>
				</c:if>
				
				<img class="paperclip" src=<t:assetspath file="paperclip.png" type="img" /> alt="paperclip" />
				<div class="sidebar">
					<h3>Latest News</h3>
					<div class="wkslider">
						<div>
							<h4>Common Expenses Goes Live</h4>
							<h5>1st Sept 2011</h5>
							<p>We have just launched our new application. Take a look around, we'd love to know what you think.....</p>
						</div>
						<div>
							<h4>We are working...</h4>
							<h5>31st Aug 2011</h5>
							<p>We re working on  Common Expenses, stay tuned...</p>
						</div>
						<div>
							<h4>Developing Blog</h4>
							<h5>21st Aug 2011</h5>
							<p>If you want to know how we are building Common Expenses chexk out our <a href="http://blog.dalendev.com/" target="_blank">blog</a></p>
						</div>						
					</div>					
				</div>
			</div>