<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Home</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/myhome.css">
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
		</div>
		<div>
			<c:choose>
				<c:when test="${currentUser.id == null}">
					<a href="/login" class="btn btn-login me-1">Login</a>
					<a href="/registration " class="btn btn-signup">Sign Up</a>
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
	<div class="section body-main">
		<a href="#studygroups" class="studygroups">Study Groups</a> | 
		<a href="#jobposts" class="jobposts">Job Posts</a> | 
		<a href="#interviewprepposts" class="interviewposts">Interview Prep Posts</a> | 
		<a href="#lifestyleposts" class="lifestyleposts">Lifestyle Posts</a> | 
		<a href="#successstories" class="successstories">Success Stories</a> 
		<h3 class="mt-5 studygroups" id="studygroups">My Study Groups</h3>
		<h3 class="mt-5 jobposts" id="jobposts">My Job Posts</h3>
		<table class="table table-striped">
			<thead>
	   			<tr>
	   				<th class="table-header">Title</th>
	   				<th class="table-header">Headline</th>
	   				<th class="table-header">Posted on</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   			<c:forEach var="eachJobPost" items="${allJobPostList}">
	   				<tr>
	   					<td class="table-body"><a href="/jobposts/${eachJobPost.id }"><c:out value="${eachJobPost.title }"/></a></td>
	   					<td class="table-body"><c:out value="${eachJobPost.headline }"/></td>
						<td class="table-body"><fmt:formatDate pattern="MMM dd, yyyy" value="${eachJobPost.createdAt }"/></td>
	   				</tr>
	   			</c:forEach>
	   		</tbody>
	   </table>
	   <h3 class="mt-5 interviewposts" id="interviewprepposts">My Interview Prep Posts</h3>
	   <table class="table table-striped">
			<thead>
	   			<tr>
	   				<th class="table-header">Title</th>
	   				<th class="table-header">Headline</th>
	   				<th class="table-header">Posted on</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   			<c:forEach var="eachInterviewPost" items="${allInterviewPostList}">
	   				<tr>
	   					<td class="table-body"><a href="/interviewposts/${eachInterviewPost.id }"><c:out value="${eachInterviewPost.title }"/></a></td>
	   					<td class="table-body"><c:out value="${eachInterviewPost.headline }"/></td>
						<td class="table-body"><fmt:formatDate pattern="MMM dd, yyyy" value="${eachInterviewPost.createdAt }"/></td>
	   				</tr>
	   			</c:forEach>
	   		</tbody>
	   </table>
	   <h3 class="mt-5 lifestyleposts" id="lifestyleposts">My Lifestyle Posts</h3>
	   <table class="table table-striped">
			<thead>
	   			<tr>
	   				<th class="table-header">Title</th>
	   				<th class="table-header">Headline</th>
	   				<th class="table-header">Posted on</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   			<c:forEach var="eachLifestylePost" items="${allLifestylePostList}">
	   				<tr>
	   					<td class="table-body"><a href="/lifestyleposts/${eachLifestylePost.id }"><c:out value="${eachLifestylePost.title }"/></a></td>
	   					<td class="table-body"><c:out value="${eachLifestylePost.headline }"/></td>
						<td class="table-body"><fmt:formatDate pattern="MMM dd, yyyy" value="${eachLifestylePost.createdAt }"/></td>
	   				</tr>
	   			</c:forEach>
	   		</tbody>
	   </table>
	   <h3 class="mt-5 successstories" id="successstories">My Success Stories</h3>
	   <table class="table table-striped mb-5">
			<thead>
	   			<tr>
	   				<th class="table-header">Title</th>
	   				<th class="table-header">Headline</th>
	   				<th class="table-header">Posted on</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   			<c:forEach var="eachSuccessStory" items="${allSuccessStoryList}">
	   				<tr>
	   					<td class="table-body"><a href="/lifestyleposts/${eachSuccessStory.id }"><c:out value="${eachSuccessStory.title }"/></a></td>
	   					<td class="table-body"><c:out value="${eachSuccessStory.headline }"/></td>
						<td class="table-body"><fmt:formatDate pattern="MMM dd, yyyy" value="${eachSuccessStory.createdAt }"/></td>
	   				</tr>
	   			</c:forEach>
	   		</tbody>
	   </table>
	</div>
	<div class="section footer footer-sub">
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