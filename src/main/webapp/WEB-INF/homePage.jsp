<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
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
			<a href="/login" class="btn btn-login">Login</a>
			<a href="/registration " class="btn btn-signup">Sign Up</a>
		</div>
	</div>
	<div class="topper"></div>
	
	<div class="home-banner">
		<h1>Trying to break into software <br /> development?</h1>
		<h3>Come and Join us!</h3>
	</div>

	<h1>Welcome, <c:out value="${currentUser.userName}"></c:out></h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
    <h1 class="mt-5">Contact Form</h1>
    <form:form method="POST" action="/home/contactus" modelAttribute="newContact" class="w-50">
    	<div>
			<form:label class="form-label" path="contactName">Your Name: </form:label>
			<form:input type="text" class="form-control" path="contactName"/>
			<form:errors path="contactName" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="formAddress">Email Address: </form:label>
			<form:input type="text" class="form-control" path="formAddress"/>
			<form:errors path="formAddress" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="phoneNumber">Phone Number: </form:label>
			<form:input type="tel" class="form-control" path="phoneNumber"/>
			<form:errors path="phoneNumber" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="formSubject">Subject: </form:label>
			<form:input type="tel" class="form-control" path="formSubject"/>
			<form:errors path="formSubject" class="text-danger"/>
		</div>
		<div>
			<form:label class="form-label" path="formMessage">Your Message: </form:label>
			<form:textarea class="form-control" path="formMessage"/>
			<form:errors path="formMessage" class="text-danger"/>
		</div>
		<input type="submit" class="btn btn-primary mt-3" />
    </form:form>
</body>
</html>