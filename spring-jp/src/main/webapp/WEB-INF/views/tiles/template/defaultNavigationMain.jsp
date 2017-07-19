<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
		        <div class="dropdown-menu sjp-default-nav-main-dropdown" aria-labelledby="navbarShowcasesDropdown">
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