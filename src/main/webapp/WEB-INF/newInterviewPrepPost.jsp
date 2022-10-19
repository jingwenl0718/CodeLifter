<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Interview Prep Post</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/interviewpost.css">
</head>
<body>
	<div class="header">
		<a href="/" class="logo">CodeLifer</a>
		<div class="navbar">
			<a href="/studygroups">Study Groups</a>
			<a href="/dashboard/jobposts">Jobs</a>
			<a href="/dashboard/interviewposts">Interview Prep</a>
			<a href="/dashboard/lifestyleposts">LifeStyle</a>
			<a href="/dashboard/successstories">Success Stores</a>
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
		<div class="text-center">
		   <h1 class="interviewpost-heading">Create an Interview-Prep Post</h1>
		</div>
	   <form:form action="/interviewposts/new" method="POST" enctype="multipart/form-data" modelAttribute="newInterviewPrepPost" class="form mt-3">
	   		<p>
	   			<form:label class="table-header" path="title">Title: </form:label>
	   			<form:input class="form-control" path="title"/>
	   			<form:errors style="color:red" path="title"/>
	   		</p>
	   		<p>
	   			<form:label class="table-header" path="headline">Headline: </form:label>
	   			<form:input class="form-control" path="headline"/>
	   			<form:errors style="color:red" path="headline"/>
	   		</p>
	   		<p>
	   			<form:label class="table-header" path="description">Description: </form:label>
	   			<form:textarea cols="30" rows="8" class="form-control" path="description"/>
	   			<form:errors style="color:red" path="description"/>
	   		</p>
	   		<p>
	   			<form:label class="table-header" path="image">Image: </form:label>
	   			<form:input type="file" path="image" accept="image/png, image/jpeg"/>	   		
	   		</p>
	   		<form:hidden path="interviewPrepPostCreator" value="${currentUser.id}"/>
	   		<div class="d-flex justify-content-end">
		   		<a class="btn btn-secondary button-post" href="/dashboard/interviewposts">Cancel</a>
		   		<button class="btn newpost-button" type="submit">Submit</button>
	   		</div>
	   </form:form>
	</div>
	<div class="section footer footer-sub">
    	<div class="d-flex align-items-center justify-content-between">
    		<div>
    			<a href="/" class="footer-logo">CodeLifer</a>
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