<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit my Study Group</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="container mt-4">
	<div class="d-flex align-items-center justify-content-between">
		<h1>Edit Study Group</h1>
		<a href="/studygroups" class="btn btn-secondary">Study Group Page</a>
	</div>
	<form:form method="POST" action="/studygroups/edit/${editStudyGroup.id}" modelAttribute="editStudyGroup" class="mt-5">
		<input type="hidden" name="_method" value="put" />
		<div>
			<form:label class="form-label" path="studyCategory">Study Category: </form:label>
			<form:select class="form-select" path="studyCategory">
				<form:option path="studyCategory" value="Data Structures">Data Structures</form:option>
				<form:option path="studyCategory" value="SQL">SQL</form:option>
				<form:option path="studyCategory" value="Arrays">Arrays</form:option>
				<form:option path="studyCategory" value="Hashes">Hashes</form:option>
				<form:option path="studyCategory" value="Algorithms">Algorithms</form:option>
				<form:option path="studyCategory" value="Mock Interview sessions">Mock Interview Sessions</form:option>
			</form:select>
			<form:errors path="studyCategory" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="studyDate">Date & Time of Meetup: </form:label>
			<form:input type="datetime-local" class="form-control" path="studyDate"/>
			<form:errors path="studyDate" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="numberGroupMembers">Number of Group Members: </form:label>
			<form:input type="number" class="form-control" path="numberGroupMembers"/>
			<form:errors path="numberGroupMembers" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="difficultyLevel">Difficulty Level: </form:label>
			<form:select class="form-select" path="difficultyLevel">
				<form:option path="difficultyLevel" value="Easy">Easy</form:option>
				<form:option path="difficultyLevel" value="Intermediate">Intermediate</form:option>
				<form:option path="difficultyLevel" value="Hard">Hard</form:option>
			</form:select>
			<form:errors path="difficultyLevel" class="text-danger"/>
		</div>
		<form:hidden path="lead" />
		<input type="submit" class="btn btn-primary mt-3" value="Submit" />
	</form:form>
	<form method="POST" action="/studygroups/delete/${editStudyGroup.id}">
		<input type="hidden" name="_method" value="delete">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<input type="submit" value="Delete" class="btn btn-danger mt-3">
	</form>
</body>
</html>