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
<link rel="stylesheet" type="text/css" href="/css/studyGroup.css">
</head>
<body>
	<div class="header">
		<a href="/" class="logo">CodeLifter</a>
		<div class="navbar">
			<a href="/studygroups">Study Groups</a>
			<a href="/dashboard/jobposts">Jobs</a>
			<a href="/dashboard/interviewposts">Interview Prep</a>
			<a href="/dashboard/lifestyleposts">LifeStyle</a>
			<a href="/dashboard/successstories">Success Stories</a>
			<a href="/myhome/${currentUser.id}">My Home</a>
		</div>
		<div>
			<c:choose>
				<c:when test="${currentUser.id == null}">
					<a href="/login" class="btn btn-login me-1">Login</a>
				</c:when>
				<c:otherwise>
					<form id="logoutForm" method="POST" action="/logout">
       					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        				<input type="submit" value="Logout!" class="btn btn-logout"/>
    				</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="topper"></div>
	
	<div class="section">
		<div class="d-flex justify-content-between align-items-center mb-5">
			<h1 class="details-h1">Study Category: <c:out value="${studyGroup.studyCategory}" /></h1>
			<a href="/studygroups" class="btn btn-back">Back</a>
		</div>
		<div class="d-flex justify-content-between align-items-start">
			<div class="col-1 text-center details">
				<h6 class="details-heading">Study Group Details</h6>
				<p><span>Date and Time: </span><fmt:formatDate value="${studyGroup.studyDate}" pattern="MMMM dd, yyyy hh:mm aa"/></p>
				<p><span>Meeting Length: </span><c:out value="${studyGroup.meetingLength}" /></p>
				<p><span>Number of Group Members: </span><c:out value="${ studyGroup.numberGroupMembers}" /></p>
				<p><span>Difficulty Level: </span><c:out value="${ studyGroup.difficultyLevel}" /></p>
			</div>
			<div class="col-1 text-center details">
				<h6 class="details-heading">All Study Group Members</h6>
				<p><span>Group Lead: </span><c:out value="${studyGroup.lead.userName}" /></p>
				<c:forEach var="member" items="${studyGroup.users}">
				<c:if test="${member.userName != studyGroup.lead.userName }">
					<p><c:out value="${member.userName}" /></p>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="d-flex align-items-center section">
		<c:if test="${studyGroup.lead.id == currentUser.id}">
			<a href="/studygroups/edit/${studyGroup.id}" class="btn btn-study me-3">Edit</a>
			<form method="POST" action="/studygroups/delete/${editStudyGroup.id}">
				<input type="hidden" name="_method" value="delete">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    	<input type="submit" value="Delete" class="btn btn-del">
			</form>
		</c:if>
	</div>
	<div class="section footer footer-color mt-5">
    	<div class="d-flex align-items-center justify-content-between">
    		<div>
    			<a href="/" class="footer-logo">CodeLifter</a>
    		</div>
    		<div>
    			<p>@CopyRight 2022</p>
    		</div>
    		<div>
    			<p>Developed by: Jingwen Li - Evelyn Valles</p>
    		</div>
    	</div>
    </div>
</body>
</html>