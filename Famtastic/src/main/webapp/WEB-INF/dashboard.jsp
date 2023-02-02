<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Dashboard</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<div class="row">
        <div class="col-md-4">
            <a href="/welcome">
                <button type="button" class="btn btn-primary">Welcome, <c:out value="${user.username}"/></button>
            </a>
            <a href="/album/new">
                <button type="button" class="btn btn-primary">Create a New Album</button>
            </a>
            <a href="/logout">
                <button type="button" class="btn btn-primary active">Logout</button>
            </a>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4">
        <h1>Your Albums</h1>
		<table class="table table-striped">
		<thead>
			<tr>
				<th>Album Name</th>
				<th>Actions</th>
			</tr>
        </thead>
        <c:forEach var="album" items="${album}">
        <tr>
            <td><a href="album/${album.id}"><c:out value="${album.album_name}"/></a></td>
            <c:if test = "${album.user.id==user.id}">
            <td>
            <form:form action="/delete/${album.id}" method="delete">
            <input type="hidden" name="album" value="delete">
            <input type="submit" value="Delete">
            </form:form>
            </td>
            </c:if>
        </tr>
        </c:forEach>
		
		</table>
		</div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
	</div>
</div>
</body>
</html>