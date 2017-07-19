<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Main Navigation -->

<div class="col-12">
	<nav>
	    <ul class="nav justify-content-center">
			<li class="nav-item">
	        	<a class="nav-link" href="<spring:url value="/admin/users-management" />" ><spring:message code="sjp.admin.nav.main.usersManagement" /></a>
	      	</li>
	      	<li class="nav-item">
	        	<a class="nav-link" href="<spring:url value="/admin/ocs" />" ><spring:message code="sjp.admin.nav.main.ocsManagement" /></a>
	      	</li>
	  	</ul>			
	</nav>
</div>