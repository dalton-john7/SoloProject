<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ page isErrorPage="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Create an Album</title>
</head>
<body>
<div class="container">
<div class="row">
        <div class="col-md-4">
            <a href="/welcome">
                <button type="button" class="btn btn-primary">Home</button>
            </a>
            <a href="/logout">
                <button type="button" class="btn btn-primary active">Logout</button>
            </a>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
	<div class="row" style="margin-top: 30px;">
        <div class="col-md-4" style="margin-top: 30px;">
	<form:form action="/album/create" modelAttribute="album" method="post">
		<form:errors path="album_name" class="error"/>
		<form:label path="album_name">Album Name</form:label>
		<form:input path="album_name" type="text" class="form-control"/>
        <input type="submit" value="Submit"/>
    </form:form>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>
