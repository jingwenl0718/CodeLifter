<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Study Group Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="container mt-4">
	<div class="d-flex align-items-center justify-content-between">
		<h1>Study Group Details</h1>
		<a href="/studygroups" class="btn btn-secondary">Study Groups Page</a>
	</div>
	
	<h1 class="mt-5">Study Category: <c:out value="${studyGroup.studyCategory}" /></h1>
	<p>Date and Time: <fmt:formatDate value="${studyGroup.studyDate}" pattern="MMMM dd, yyyy hh:mm aa"/></p>
	<p>Meeting Length: <c:out value="${studyGroup.meetingLength}" /></p>
	<p>Number of Group Members: <c:out value="${ studyGroup.numberGroupMembers}" /></p>
	<p>Difficulty Level: <c:out value="${ studyGroup.difficultyLevel}" /></p>
	<h6>All Study Group Members</h6>
	<p>Group Lead: <c:out value="${studyGroup.lead.userName}" /></p>
	<c:forEach var="member" items="${studyGroup.users}">
	<c:if test="${member.userName != studyGroup.lead.userName }">
		<p><c:out value="${member.userName}" /></p>
	</c:if>
	</c:forEach>
</body>
</html>