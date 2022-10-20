<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
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
			<a href="/#contactus">Contact Us</a>
		</div>
		<div>
			<c:choose>
				<c:when test="${currentUser.id == null}">
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
	<div class="topper"></div>
	
	<div class="d-flex align-items-center justify-content-center">
		<div class="my-4 login-box">
			<h1 class="text-center">Sign In to Access Your Account</h1>
			<form method="POST" action="/login" class="my-5">
		        <div>
		            <label for="email">Email</label>
		            <input type="text" id="email" name="email" class="form-input" placeholder="Email"/>
		        </div>
		        <div>
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password" class="form-input" placeholder="Password"/>
		        </div>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <div class="d-flex align-items-center justify-content-between mb-3">
		        	<input type="submit" value="Login!" class="btn-log btn"/>
		        	<p>Don't have an account yet?
		        	<a href="/registration" class="reg-link ms-3">Register Here</a></p>
		        </div>
		        	<c:if test="${logoutMessage != null}">
        				<c:out value="${logoutMessage}"></c:out>
				    </c:if>
				    <c:if test="${errorMessage != null}">
				        <c:out value="${errorMessage}"></c:out>
				    </c:if>
		    </form>
		</div>
	</div>
	<div class="section footer footer-color-log mt-5">
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