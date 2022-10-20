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
	<div class="d-flex align-items-center justify-content-between section">
		<h1>Edit Study Group</h1>
		<a href="/studygroups" class="btn btn-back">Back</a>
	</div>
	
	<div class="section d-flex align-items-center justify-content-center edit-form">
		<form:form method="POST" action="/studygroups/edit/${editStudyGroup.id}" modelAttribute="editStudyGroup">
			<input type="hidden" name="_method" value="put" />
			<div>
				<form:label class="form-label" path="studyCategory">Study Category: </form:label>
				<form:select class="form-input" path="studyCategory">
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
				<form:input type="datetime-local" class="form-input" path="studyDate"/>
				<form:errors path="studyDate" class="text-danger"/>
			</div>
			<div>
				<form:label class="form-label" path="numberGroupMembers">Number of Group Members: </form:label>
				<form:input type="number" class="form-input" path="numberGroupMembers"/>
				<form:errors path="numberGroupMembers" class="text-danger"/>
			</div>
			<div>
				<form:label class="form-label" path="difficultyLevel">Difficulty Level: </form:label>
				<form:select class="form-input" path="difficultyLevel">
					<form:option path="difficultyLevel" value="Easy">Easy</form:option>
					<form:option path="difficultyLevel" value="Intermediate">Intermediate</form:option>
					<form:option path="difficultyLevel" value="Hard">Hard</form:option>
				</form:select>
				<form:errors path="difficultyLevel" class="text-danger"/>
			</div>
			<form:hidden path="lead" />
			<input type="submit" class="btn btn-study mt-3 me-3" value="Submit" />
			<form method="POST" action="/studygroups/delete/${editStudyGroup.id}">
				<input type="hidden" name="_method" value="delete">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    	<input type="submit" value="Delete" class="btn btn-del mt-3">
			</form>
		</form:form>
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