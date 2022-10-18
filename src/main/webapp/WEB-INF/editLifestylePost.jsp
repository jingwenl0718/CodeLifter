<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Lifestyle Post</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container mt-5">
	   <h1>Edit a Lifestyle Post</h1>
	   <form:form action="/lifestyleposts/${newLifestylePost.id }/edit" method="POST" modelAttribute="newLifestylePost" class="form col-7 mt-5">
	   		<input type="hidden" name="_method" value="put"/>
	   		<p>
	   			<form:label class="h5" path="title">Title: </form:label>
	   			<form:input class="ml-5 form-control" path="title"/>
	   			<form:errors style="color:red" path="title"/>
	   		</p>
	   		<p>
	   			<form:label class="h5" path="headline">Headline: </form:label>
	   			<form:input class="ml-5 form-control" path="headline"/>
	   			<form:errors style="color:red" path="headline"/>
	   		</p>
	   		<p>
	   			<form:label class="h5" path="description">Job Description: </form:label>
	   			<form:textarea cols="30" rows="10" class="form-control" path="description"/>
	   			<form:errors style="color:red" path="description"/>
	   		</p>
	   		<form:hidden path="image"/>
	   		<form:hidden path="lifestylePostCreator"/>
	   		<div class="d-flex justify-content-end">
		   		<a class="btn btn-secondary" href="/dashboard/lifestyleposts">Cancel</a>
		   		<button class="btn btn-danger" type="submit">Submit</button>
	   		</div>
	   </form:form>
	</div>
</body>
</html>