<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Main Navigation -->

<div class="col-12">
	<nav>
	    <ul class="nav justify-content-center">
			<li class="nav-item">
	        	<a class="nav-link" href="<spring:url value="/index" />" ><spring:message code="default.nav.main.home" /></a>
	      	</li>
	      	<li class="nav-item">
	        	<a class="nav-link" href="<spring:url value="/user/ocs" />" ><spring:message code="sjp.user.nav.main.ocs" /></a>
	      	</li>
	      	<li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarUserSocialServicesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		        	<i class="fa fa-long-arrow-down" aria-hidden="true"></i><spring:message code="sjp.user.nav.main.social" />
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarUserSocialServicesDropdown">
		        	<a class="nav-link" href="<spring:url value="/user/social/twitter-connect" />" >
		        		<i class="fa fa-twitter" aria-hidden="true"></i><spring:message code="sjp.user.nav.main.twitter" />
		        	</a>
		        </div>
		  	</li>
	  	</ul>			
	</nav>
</div>