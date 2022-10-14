<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Job Post Details</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
   <div class="container mt-5">
	   	<div class="d-flex justify-content-between align-items-center">
	   		<h1 class="mr-5"><c:out value="${oneJobPost.title }"/></h1>
	   		<a class="btn btn-warning" href="/dashboard/jobposts">Job Dashboard</a>
	   	</div>
	   	
	   	<h5 class="text-secondary fw-light fst-italic">(Posted by <c:out value="${oneJobPost.jobPostCreator.userName }"/> )</h5>
	   	<h4 class="mt-3"><c:out value="${oneJobPost.headline }"/></h4>
	   	<p class="mt-3"><c:out value="${oneJobPost.description }"/></p>
	   	<hr/>
	    <c:choose>
  			<c:when test="${currentUser.id.equals(oneJobPost.jobPostCreator.id) }">
				<div class="d-flex justify-content-end">
					<a class="btn btn-primary" href="/jobposts/${oneJobPost.id }/edit">Edit</a>
					<form action="/jobposts/${oneJobPost.id }/delete" method="POST">
						<input type="hidden" name="_method" value="delete"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button class="btn btn-secondary" type="submit">Delete</button>	   								
					</form>
				</div>
		   	</c:when>
		</c:choose>
		<form:form action="/jobposts/${oneJobPost.id }/comment" method="POST" modelAttribute="newComment" class="form col-7 mt-5">
	   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><p>
	   			<form:label class="h5" path="content">Comment: </form:label>
	   			<form:textarea class="ml-5 form-control" path="content"/>
	   			<form:errors style="color:red" path="content"/>
	   		</p>
	   		<form:hidden path="jobPost" value="${oneJobPost.id}"/>
	   		<form:hidden path="commenter" value="${currentUser.id}"/>
	   		<div class="d-flex justify-content-end">
		   		<button class="btn btn-danger" type="submit">Add Comment</button>
	   		</div>
	   </form:form>
	   <h3>Other CodeLiters Have Said</h3>
	   <ul>
	   		<c:forEach var="oneComment" items="${allCommentList}">
                <li>
                <p><c:out value="${oneComment.commenter.userName}"/> posted:</p>
                <p><c:out value="${oneComment.content}"/></p>
                </li>
        	</c:forEach>
	   </ul>
   </div>
</body>
</html>