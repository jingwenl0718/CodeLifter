<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>LifestylePost Dashboard</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/lifestylepost.css">
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
			<a href="#contactus">Contact Us</a>
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
		<h1 class="text-center lifestylepost-heading">All Lifestyle Posts</h1>
		<div class="text-end">
	 		<a class="btn newpost-button" href="/lifestyleposts/new">+ new post</a>
		</div>
		<table class="mt-3 table table-striped">
			<thead>
	   			<tr>
	   				<th class="table-header">Title</th>
	   				<th class="table-header">Headline</th>
	   				<th class="table-header">Posted by</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   			<c:forEach var="eachLifestylePost" items="${allLifestylePostList}">
	   				<tr>
	   					<td class="table-body"><a href="/lifestyleposts/${eachLifestylePost.id }"><c:out value="${eachLifestylePost.title }"/></a></td>
	   					<td class="table-body"><c:out value="${eachLifestylePost.headline }"/></td>
						<td class="table-body"><c:out value="${eachLifestylePost.lifestylePostCreator. userName }"/></td>
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