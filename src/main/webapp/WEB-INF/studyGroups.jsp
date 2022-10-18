<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Study Groups</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="container mt-4">
	<div class="d-flex align-items-center justify-content-around">
		<h1>Create a Study Group</h1>
		<h1>Or</h1>
		<h1>Join a Study Group</h1>
		 <form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" value="Logout!" class="btn btn-danger"/>
    	</form>
	</div>
	<div class="d-flex justify-content-between align-items-center">
		<div class="col-1 flex-fill me-5">
			<form:form method="POST" action="/studygroups/new" modelAttribute="newStudyGroup" class="mt-5">
				<div>
					<form:label class="form-label" path="studyCategory">Study Category: </form:label>
					<form:select class="form-select" path="studyCategory">
						<form:option path="studyCategory" value="Data Structures">Data Structures</form:option>
						<form:option path="studyCategory" value="SQL">SQL</form:option>
						<form:option path="studyCategory" value="Arrays">Arrays</form:option>
						<form:option path="studyCategory" value="Hashes">Hashes</form:option>
						<form:option path="studyCategory" value="Algorithms">Algorithms</form:option>
						<form:option path="studyCategory" value="Mock Interview Sessions">Mock Interview Sessions</form:option>
					</form:select>
					<form:errors path="studyCategory" class="text-danger"/>
				</div>
				<div>
					<form:label class="form-label" path="studyDate">Date & Time of Meetup: </form:label>
					<form:input type="datetime-local" class="form-control" path="studyDate"/>
					<form:errors path="studyDate" class="text-danger"/>
				</div>
				<div>
					<form:label class="form-label" path="meetingLength">Length of Meeting: </form:label>
					<form:select class="form-select" path="meetingLength">
						<form:option path="meetingLength" value="30 Minutes">30 Minutes</form:option>
						<form:option path="meetingLength" value="45 Minutes">45 Minutes</form:option>
						<form:option path="meetingLength" value="1 Hour">1 Hour</form:option>
						<form:option path="meetingLength" value="1 Hour 15 Minutes">1 Hour 15 Minutes</form:option>
						<form:option path="meetingLength" value="1 Hour 30 Minutes">1 Hour 30 Minutes</form:option>
					</form:select>
					<form:errors path="meetingLength" class="text-danger"/>
				</div>
				<div>
					<form:label class="form-label" path="numberGroupMembers">Number of Group Members: </form:label>
					<form:input type="number" class="form-control" path="numberGroupMembers"/>
					<form:errors path="numberGroupMembers" class="text-danger"/>
				</div>
				<div>
					<form:label class="form-label" path="difficultyLevel">Difficulty Level: </form:label>
					<form:select class="form-select" path="difficultyLevel">
						<form:option path="difficultyLevel" value="Easy">Easy</form:option>
						<form:option path="difficultyLevel" value="Intermediate">Intermediate</form:option>
						<form:option path="difficultyLevel" value="Hard">Hard</form:option>
					</form:select>
					<form:errors path="difficultyLevel" class="text-danger"/>
				</div>
				<form:hidden path="lead" value="${currentUser.id}"/>
				<input type="submit" class="btn btn-primary mt-3" value="Submit" />
			</form:form>
		</div>
		<div class="col-5 flex-fill me-5">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Category</th>
						<th>Date</th>
						<th>Level</th>
						<th>No. of Members Requested</th>
						<th>Group Lead</th>
						<th>Join Team</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="studyGroup" items="${unassignedStudyGroups}">
						<tr>
							<c:if test="${studyGroup.lead.id != currentUser.id}">
								<td><c:out value="${studyGroup.studyCategory}" /></td>
								<td><a href="/studygroups/${studyGroup.id}"><fmt:formatDate value="${studyGroup.studyDate}" pattern="MMMM dd, yyyy hh:mm aa"/></a></td>
								<td><c:out value="${studyGroup.difficultyLevel}" /></td>
								<td><c:out value="${studyGroup.numberGroupMembers}" /></td>
								<td><c:out value="${studyGroup.lead.userName}" /></td>
								<td>
									<c:choose>
										<c:when test="${studyGroup.users.size() < studyGroup.numberGroupMembers}">
											<form method="POST" action="/studygroups/join/${studyGroup.id}">
									    		<input type="hidden" name="_method" value="put" />
									    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									    		<input type="submit" value="Join Team" class="btn btn-dark"/>
						    				</form>
						    			</c:when>
					    			<c:otherwise>
					    				Group Members filled
					    			</c:otherwise>
					    			</c:choose>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<h2 class="mt-5">Your Scheduled Study Groups</h2>
	<table class="table table-striped table-hover table-bordered mt-3">
		<thead>
			<tr>
				<th>Category</th>
				<th>Date</th>
				<th>Level</th>
				<th>No. of Members Requested</th>
				<th>Group Lead</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="studyGroup" items="${assignedStudyGroups}">
				<tr>
					<td><c:out value="${studyGroup.studyCategory}" /></td>
					<td><a href="/studygroups/${studyGroup.id}"><fmt:formatDate value="${studyGroup.studyDate}" pattern="MMMM dd, yyyy hh:mm aa"/></a></td>
					<td><c:out value="${studyGroup.difficultyLevel}" /></td>
					<td><c:out value="${studyGroup.numberGroupMembers}" /></td>
					<td><c:out value="${studyGroup.lead.userName}" /></td>
					<c:if test="${studyGroup.lead.id==currentUser.id}">
						<td><a href="/studygroups/edit/${studyGroup.id}">Edit Project</a></td>
					</c:if>
					<c:if test="${studyGroup.lead.id!=currentUser.id}">
						<td>
				       		<form method="POST" action="/studygroups/leave/${studyGroup.id}">
					    		<input type="hidden" name="_method" value="put" />
					    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					    		<input type="submit" value="Leave Team" class="btn btn-danger"/>
					    	</form>
				       </td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>