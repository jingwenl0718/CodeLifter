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
</head>
<body>
	<h1>Register</h1>
    
    <form:form method="POST" action="/registration" modelAttribute="user">
        <div>
            <form:label path="userName">User Name:</form:label>
            <form:input type="text" path="userName"/>
            <form:errors path="userName"/>
        </div>
        <div>
            <form:label path="email">Email:</form:label>
            <form:input type="text" path="email"/>
            <form:errors path="email"/>
        </div>
        <div>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
            <form:errors path="password"/>
        </div>
        <div>
            <form:label path="confirm">Password Confirmation:</form:label>
            <form:password path="confirm"/>
            <form:errors path="confirm"/>
        </div>
        <input type="submit" value="Register!"/>
    </form:form>
</body>
</html>