<%-- 
    Document   : admin.jsp
    Created on : Mar 30, 2024, 12:17:42 PM
    Author     : ta2khu75
--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link
		    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		    rel="stylesheet"
		    />
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	</head>
	<body>
		<%@ include file="admin/nav.jsp" %>
		<div class="container">
			<c:choose>
				<c:when test="${url.equals('user')}">
					<%@ include file="admin/user.jsp" %>
				</c:when>
				<c:when test="${url.equals('video')}">
					<%@ include file="admin/video.jsp" %>
				</c:when>
				<c:when test="${url.equals('statistical')}">
					<%@ include file="admin/statistical.jsp" %>
				</c:when>
                                <c:when test="${url.equals('import')}">
					<%@ include file="admin/import.jsp" %>
				</c:when>
			</c:choose>
		</div>
	
	</body>
</html>
