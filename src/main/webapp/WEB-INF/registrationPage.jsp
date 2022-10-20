<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<link rel="stylesheet" type="text/css" href="/css/registration.css"/>
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

	<h1 class="text-center mt-3">Create a New Account</h1>
	<p class="text-center mt-4">Come and join the CodeLifter community! Let's setup your account. Already have one? 
	<a href="/login">Sign in here</a>.</p>
    
    <div class="d-flex align-items-start justify-content-between mt-3 section">
    	<div class="col-1 mx-5">
    		<img src="https://envri.eu/wp-content/uploads/2016/08/software-developer-copy.jpg" alt="software engineer cartoon" class="cartoon-img"/>
    	</div>
    	<div class="col-1">
		    <form:form method="POST" action="/registration" modelAttribute="user">
		        <div>
		        	<form:label class="form-label" path="userName">User Name:</form:label>
		            <form:input type="text" path="userName" placeholder="User Name" class="form-input"/>
		            <form:errors path="userName" style="color: red"/>
		        </div>
		        <div>
		        	<form:label class="form-label" path="email">Email:</form:label>
		            <form:input type="text" path="email" placeholder="Email" class="form-input"/>
		            <form:errors path="email" style="color: red"/>
		        </div>
		        <div>
		        	<form:label class="form-label" path="password">Password:</form:label>
		            <form:password path="password" placeholder="Password" class="form-input"/>
		            <form:errors path="password" style="color: red"/>
		        </div>
		        <div>
		        	<form:label class="form-label" path="confirm">Confirm:</form:label>
		            <form:password path="confirm" placeholder="Confirm Password" class="form-input"/>
		            <form:errors path="confirm" style="color: red"/>
		        </div>
		        <input type="submit" value="Register!" class="btn btn-reg mt-5"/>
		    </form:form>
		 </div>
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