<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Main Navigation -->

<div class="col-12">
	<nav>
	    <ul class="nav justify-content-center">
			<li class="nav-item">
	        	<a class="nav-link" href="<spring:url value="/index" />" ><spring:message code="default.nav.main.home" /></a>
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