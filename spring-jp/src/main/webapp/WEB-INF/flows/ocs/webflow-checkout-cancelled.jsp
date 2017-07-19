<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Spring Java Portal (SJP)</title>
	<meta name="description" content="Spring Java Portal is a project created by Giorgio Federici to group all the Java knowledge together. The aim of the Spring Java Portal is to be a showcase and reference for the future.">
	
	<!-- TODO: Create a favicon -->
	<link rel="icon" href="/spring-jp/resources/favicon.ico" type="image/x-icon">
	
	<meta name="author" content="Giorgio Federici">
	<meta name="copyright" content="Giorgio Federici">

	<meta name="date.created" content="14-06-2017">
	
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_name" content="${_csrf.parameterName}"/>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,400">
    <link rel="stylesheet" href="/spring-jp/resources/css/style.css?version=1.1">
    
    <script src="https://use.fontawesome.com/cb4ee38d2b.js"></script>
    
</head>
<body>
	<div class="sjp-wrapper">
		
		<div class="container sjp-container">
	
			<header id="header" class="row default-header">
	            <!-- Logo and Main Title Column -->
				<div class="col-12 col-md-6">
					<div class="row">
						<div class="col-12">
							<spring:url value="/index" var="index" />
							<h1>
								<a href="${ index }" title="Spring Java Portal Homepage"> <spring:message
										code="sjp.default.header.title" />
								</a>
							</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<h6>
								<spring:message code="sjp.default.header.subtitle" />
							</h6>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<a href="?lang=it"
								title="<spring:message code="sjp.default.header.italian" />"><img
								alt="Italian flag" src="/spring-jp/resources/images/italian-flag.jpg"></a> <a
								href="?lang=en" title="<spring:message code="sjp.default.header.english" />"><img
								alt="English flag" src="/spring-jp/resources/images/english-flag.jpg"></a>
						</div>
					</div>
				</div>
				<!-- Top Right Navigation -->
				<div class="col-12 col-md-6">
					<div class="row pull-right">
						<div class="col-12">
							<nav class="navbar navbar-toggleable-sm default-navbar">
				
								<!-- Responsive Menu -->
								<button class="navbar-toggler navbar-toggler-right" type="button"
									data-toggle="collapse" data-target="#navbarSupportedContent"
									aria-controls="navbarSupportedContent" aria-expanded="false"
									aria-label="Toggle navigation">
									<span class="navbar-toggler-icon"></span>
								</button>
				
								<div class="collapse navbar-collapse navbar-top-content"
									id="navbarTopSupportedContent">
									<ul class="navbar-nav mr-auto">
										<sec:authorize access="isAnonymous()">
											<li class="nav-item">
									        	<a class="nav-link" href="<spring:url value="/index" />" ><spring:message code="sjp.default.header.nav.top.home" /></a>
									      	</li>
									      	<li class="nav-item">
									        	<a class="nav-link" href="<spring:url value="/signin" />" ><spring:message code="sjp.default.header.nav.top.signin" /></a>
									      	</li>
									      	<li class="nav-item">
									        	<a class="nav-link" href="<spring:url value="/signup" />" ><spring:message code="sjp.default.header.nav.top.signup" /></a>
									      	</li>
										</sec:authorize>
								      	<sec:authorize access="isAuthenticated()">
								      		<li class="nav-item dropdown">
										        <a class="nav-link dropdown-toggle" href="#" id="navbarUserProfileServicesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										        	<c:choose>
														<c:when test="${empty userProfileImage} ">
															<img src="http://via.placeholder.com/50x50">
														</c:when>
														<c:otherwise>
															<img src="data:image/jpeg;base64,${userProfileImage}" width="50" height="50">
														</c:otherwise>
													</c:choose>
										        </a>
										        <div class="dropdown-menu" aria-labelledby="navbarUserProfileServicesDropdown">
										        	<a class="nav-link" href="<spring:url value="/user/index" />" >
										        		<i class="fa fa-user" aria-hidden="true"></i><spring:message code="sjp.user.header.nav.top.userProfile" />
										        	</a>
										        	<form:form action="/spring-jp/logout" method="POST" cssClass="inline">
														<button type="submit" class="link-button" title="<spring:message code="sjp.user.header.nav.top.logout" />">
															<i class="fa fa-power-off" aria-hidden="true"></i>
															<spring:message code="sjp.user.header.nav.top.logout" />
														</button>
										      		</form:form>
										        </div>
										  	</li>
							      		</sec:authorize>
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
	        </header>
	        
	        
	         <section class="row navigation-main">
	        	<!-- Main Navigation -->

				<div class="col-12">
					<nav>
					    <ul class="nav justify-content-center">
							<li class="nav-item">
					        	<a class="nav-link" href="<spring:url value="/index" />" ><spring:message code="default.nav.main.home" /></a>
					      	</li>
					      	<li class="nav-item dropdown">
						        <a class="nav-link dropdown-toggle" href="<spring:url value="/showcases" />" id="navbarShowcasesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						        	<spring:message code="default.nav.main.showcases" />
						        </a> 
						        <div class="dropdown-menu" aria-labelledby="navbarShowcasesDropdown">
						        	<a class="nav-link" href="<spring:url value="/showcases" />" ><spring:message code="default.nav.main.showcases.all" /></a>
						        	<a class="nav-link" href="<spring:url value="/showcases/ocs" />" ><spring:message code="default.nav.main.showcases.onlineCartSystem" /></a>
						        </div>
						  	</li>
					      	<li class="nav-item">
					        	<a class="nav-link" href="<spring:url value="/architecture" />" ><spring:message code="default.nav.main.architecture" /></a>
					      	</li>
					      	<!-- 
					      	<li class="nav-item">
					        	<a class="nav-link" href="<spring:url value="/technologies" />" ><spring:message code="default.nav.main.technologies" /></a>
					      	</li>
					      	 -->
					      	<li class="nav-item">
					        	<a class="nav-link" href="<spring:url value="/about" />" ><spring:message code="default.nav.main.about" /></a>
					      	</li>
					  	</ul>			
					</nav>
				</div>	
	        </section>     

			<section class="row">
				<div class="col-12 text-center">
					<h2 class="alert alert-warning"><spring:message code="sjp.showcases.ocs.webflow.checkout.cancelled.description" /></h2>
				</div>
			</section>
			
			<hr>
	        
	        <footer id="footer" class="row">
	        	<div class="col-12 col-md-4 offset-md-4">
					<p>&copy; Copyright 2017 <a href="http://www.giorgiofederici.it/" target="_blank" title="Giorgio Federici's Website">Giorgio Federici </a></p>
				</div>
	        </footer>     
           
		</div>
		
	</div>	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

</body>
</html>