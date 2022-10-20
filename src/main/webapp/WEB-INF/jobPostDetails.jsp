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
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/jobpost.css">
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
	   		<h1 class="jobpost-heading"><c:out value="${oneJobPost.title }"/></h1>
	   	</div>
	   	<div class="d-flex align-items-top details-body">
		   	<div class="body-left">
			   	<img src="<c:url value='${oneJobPost.photosImagePath}'></c:url>" alt="image" class="details_image"/>
				<div class="d-flex mt-3">
			   		<h6 class="me-1">Posted by <c:out value="${oneJobPost.jobPostCreator.userName }"/></h6>
					<c:choose>
						<c:when test="${oneJobPost.updatedAt != null }">
							<h6 class="text-secondary fw-light fst-italic">(<fmt:formatDate pattern="MMM dd, yy hh:mm a" type="both" value="${oneJobPost.updatedAt }"/>)</h6>
						</c:when>
						<c:otherwise>
							<h6 class="text-secondary fw-light fst-italic">(<fmt:formatDate pattern="MMM dd, yy hh:mm a" type="both" value="${oneJobPost.createdAt }"/>)</h6>
						</c:otherwise>
					</c:choose>
				</div>
		   	</div>
		   	<div class="body-right">
			   	<h4><c:out value="${oneJobPost.headline }"/></h4>
			   	<p class="mt-3 post-description"><c:out value="${oneJobPost.description }"/></p>
		   	</div>
	   	</div>
	   	<hr/>
	   	<div class="d-flex justify-content-end">
			<c:if test="${currentUser.id.equals(oneJobPost.jobPostCreator.id) }">
				<div class="d-flex">
					<a class="btn newpost-button button-post" href="/jobposts/${oneJobPost.id }/edit">Edit</a>
					<form action="/jobposts/${oneJobPost.id }/delete" method="POST">
						<input type="hidden" name="_method" value="delete"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button class="btn btn-secondary button-post" type="submit">Delete</button>	   
					</form>
				</div>
		   	</c:if>
			<a class="btn btn-warning button-dashboard" href="/dashboard/jobposts">Jobs Dashboard</a>								
	   	</div>
		<form:form action="/jobposts/${oneJobPost.id }/comment" method="POST" modelAttribute="newComment" class="form mt-3">
	   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><p>
	   			<form:label class="h5" path="content">Comment: </form:label>
	   			<form:textarea class="form-control" rows="4" path="content"/>
	   			<form:errors style="color:red" path="content"/>
	   		</p>
	   		<form:hidden path="jobPost" value="${oneJobPost.id}"/>
	   		<form:hidden path="commenter" value="${currentUser.id}"/>
	   		<div class="d-flex justify-content-end">
		   		<button class="btn newpost-button" type="submit">Add Comment</button>
	   		</div>
	   </form:form>
	   <c:choose>
	   		<c:when test="${allCommentList.size()!=0}">
			   <div class="text-center mb-3">
			   		<h3 class="comments">Other CodeLiters Have Said</h3>
			   </div>
			   <div class="mb-5">
				   <ul class="mb-3 list">
				   		<c:forEach var="oneComment" items="${allCommentList}">
			                <li class="comment-list">
			                <p><c:out value="${oneComment.commenter.userName}"/> posted on <fmt:formatDate pattern="MMM dd, yy hh:mm a" type="both" value="${oneComment.createdAt }"/>:</p>
			                <p class="comment-content"><c:out value="${oneComment.content}"/></p>
			                </li>
			        	</c:forEach>
				   </ul>
			   </div>
	   		</c:when>
	   		<c:otherwise>
	   			<div class="text-center mb-3">
			   		<h3 class="comments">You can be the first one to leave a comment! :)</h3>
			   </div>
	   		</c:otherwise>
	   </c:choose>
   </div>
   <div class="section footer footer-sub-details">
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