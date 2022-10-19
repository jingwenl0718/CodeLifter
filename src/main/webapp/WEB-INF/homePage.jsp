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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
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
	<div class="topper"></div>
	
	<div class="home-banner">
		<h1>Trying to break into software <br /> development?</h1>
		<h3>Come and Join us!</h3>
	</div>
	
	<c:if test="${currentUser.id != null}">
		<h1 class="text-center my-5">Welcome, <c:out value="${currentUser.userName}"></c:out></h1>
	</c:if>
	
	<div class="section d-flex mt-3">
		<div class="col-4 me-5">
			<div class="success-post-left">
				<img src="src\main\resources\static\successstory-image\4\success_story_2.png" alt="success story"/>
				<div class="text-center overlay">
					<p>October 18th, 2022</p>
					<h1>How I landed my dream job</h1>
				</div>
			</div>
		</div>
		<div class="col-2">
			<div class="success-post-right mb-2">
				<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="success story"/>
				<div class="center overlay">
					<p>October 18th, 2022</p>
					<h1>How I landed my dream job</h1>
				</div>
			</div>
			<div class="success-post-right">
				<img src="src\main\resources\static\successstory-image\7\success_story_5.jpeg" alt="success story"/>
				<div class="text-center overlay">
					<p>October 18th, 2022</p>
					<h1>How I landed my dream job</h1>
				</div>
			</div>
		</div>
	</div>
	
	<div class="section">
		<div class="post-box d-flex align-items-center justify-content-between ">
			<div class="img-box">
				<img src="src\main\resources\static\interviewpost-image\1\intervivew_prep_image2.png" alt="study group" />
			</div>
			<div class="content">
				<h1>How a study group helped me in interview prep</h1>
				<p>Here's where you can provide a short except or summary of the article to tease the reader to click your link... Read more</p>
			</div>
		</div>
		<div class="post-box d-flex align-items-center justify-content-between">
			<div class="img-box">
				<img src="src\main\resources\static\lifestylepost-image\1\yoga.jpeg" alt="study group" />
			</div>
			<div class="content">
				<h1>Why work-life balance is so essential during the career changing process</h1>
				<p>Here's where you can provide a short except or summary of the article to tease the reader to click your link... Read more</p>
			</div>
		</div>
	</div>
	
	<div class="section recent">
		<h1 class="text-center">Recent Posts</h1>
		<div class="swiper recent-slider mt-5">
			<div class="swiper-wrapper wrapper">
				
				<div class="swiper-slide">
					<div class="box">
						<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="Study Groups" />
						<p class="recent-date">October 18th, 2022 by user1</p>
						<h3>Post 1</h3>
						<p class="content">Content</p>
						<div class="link">
							<a href="">Read More</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box">
						<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="Study Groups" />
						<p class="recent-date">October 18th, 2022 by user1</p>
						<h3>Post 2</h3>
						<p class="content">Content</p>
						<div class="link">
							<a href="">Read More</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box">
						<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="Study Groups" />
						<p class="recent-date">October 18th, 2022 by user1</p>
						<h3>Post 3</h3>
						<p class="content">Content</p>
						<div class="link">
							<a href="">Read More</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box">
						<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="Study Groups" />
						<p class="recent-date">October 18th, 2022 by user1</p>
						<h3>Post 4</h3>
						<p class="content">Content</p>
						<div class="link">
							<a href="">Read More</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box">
						<img src="src\main\resources\static\successstory-image\6\success_story_4.jpeg" alt="Study Groups" />
						<p class="recent-date">October 18th, 2022 by user1</p>
						<h3>Post 5</h3>
						<p class="content">Content</p>
						<div class="link">
							<a href="">Read More</a>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
    
    <div class="d-flex align-items-center justify-content-between contact-box mt-5" id="contactus">
	    <div class="contact col-3">
		    <h1 class="mb-4">Contact Us</h1>
		    <form:form method="POST" action="/home/contactus" modelAttribute="newContact" class="contact-form">
		    	<h3>Send us a message</h3>
		    	<p>Send us a message and we'll respond within 24 hours.</p>
		    	<div class="input-box">
		    		<div>
						<form:label class="form-label" path="contactName">Your Name: </form:label>
						<form:input type="text" class="form-control mb-1" path="contactName"/>
						<form:errors path="contactName" class="text-danger"/>
					</div>
					<div>
						<form:label class="form-label" path="formAddress">Email Address: </form:label>
						<form:input type="text" class="form-control mb-1" path="formAddress"/>
						<form:errors path="formAddress" class="text-danger"/>
					</div>
				</div>
				<div class="input-box">
					<div>
						<form:label class="form-label" path="phoneNumber">Phone Number: </form:label>
						<form:input type="tel" class="form-control mb-1" path="phoneNumber"/>
						<form:errors path="phoneNumber" class="text-danger"/>
					</div>
					<div>
						<form:label class="form-label" path="formSubject">Subject: </form:label>
						<form:input type="tel" class="form-control mb-1" path="formSubject"/>
						<form:errors path="formSubject" class="text-danger"/>
					</div>
				</div>
				<div class="text-box">
					<form:label class="form-label" path="formMessage">Your Message: </form:label>
					<form:textarea class="form-control mb-1" path="formMessage"/>
					<form:errors path="formMessage" class="text-danger"/>
				</div>
				<input type="submit" class="btn btn-contact mt-3" />
		    </form:form>
	    </div>
	    <div class="col-1">
	    	<div style="height: 70px"></div>
	    	<div class="contact-info">
	    		<h3 class="mb-5">Contact Information</h3>
	    		<p>Address: 177 Huntington Ave Ste 1703</p>
	    		<p>Email: contact-us@company.com</p>
	    		<p>Phone Number: +1-617-555-0108</p>
	    	</div>
	    </div>
    </div>
    
    <div class="section footer">
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
    
    <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
    <script>
    		var swiper = new Swiper(".recent-slider", {
    			spaceBetween: 20,
    			loop: true,
    			autoPlay: {
    				delay: 2500,
    				disableOnInteraction: false,
    			},
    			breakpoints: {
    				640: {
    					slidesPerView: 1,
    				},
    				768: {
    					slidesPerView: 2,
    				},
    				1024: {
    					slidesPerView: 3,
    				},
    			}
    		});
    </script>
</body>
</html>