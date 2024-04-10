<%-- Document : managerUser Created on : Dec 1, 2023, 3:25:33 PM Author :
ta2khu75 --%> 
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link
		    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		    rel="stylesheet"
		    />
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		<title>JSP Page</title>
	</head>
	<body>
		
		<%@ include file="part/nav.jsp" %>
		<div class="container">
			<c:choose>
				<c:when test="${url.equals('home')}">
					<%@ include file="part/article.jsp" %>
				</c:when>
				<c:when test="${url.equals('video_details')}">
					<%@ include file="part/video_details.jsp" %>
				</c:when>
				<c:when test="${url.equals('login')}">
					<%@ include file="part/login.jsp" %>
				</c:when>
				<c:when test="${url.equals('change')}">
					<%@ include file="part/change.jsp" %>
				</c:when>
				<c:when test="${url.equals('register')}">
					<%@ include file="part/register.jsp" %>
				</c:when>
				<c:when test="${url.equals('forget')}">
					<%@ include file="part/forget.jsp" %>
				</c:when>
				<c:when test="${url.equals('admin/user')}">
					<%@ include file="admin/user.jsp" %>
				</c:when>
				<c:when test="${url.equals('admin/video')}">
					<%@ include file="admin/video.jsp" %>
				</c:when>	
				<c:otherwise> <%@ include file="part/nav.jsp" %> </c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
