<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>${album.album_name}</title>
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
            <div class="btn-group">
                <a href="/welcome">
                    <button type="button" class="btn btn-primary">Welcome, <c:out value="${user.username}"/></button>
                </a>
                <a href="/logout">
                    <button type="button" class="btn btn-primary active">Logout</button>
                </a>
            </div>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <h3>Now Viewing ${album.album_name}</h3>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <table class="table table-striped">
            <thead>
		          <tr>
		            <th>
		            	Photo URL's
		           	</th>
		          </tr>
		          </thead>    
                        <c:forEach var="photo" items="${photo}">
                            <tr>
                                <td><c:out value="${photo.photo_url}"/></td>
                            </tr>
                        </c:forEach>
                    
                    </table>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <form:form action="/addPhoto/${album.id}" modelAttribute="newphoto" method="post">
                    <form:errors path="photo_url" class="error"/>
                    <form:label path="photo_url">Photo URL</form:label>
                    <form:input path="photo_url" type="text" class="form-control"/>
                    <input type="submit" value="Submit"/>
                </form:form>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>