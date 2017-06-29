<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section class="row alert alert-danger">
	<div class="col-12 text-center">
		<div class="row">
			<div class="col-12">
				<h2><spring:message code="sjp.error.page.title" /></h2>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<h6><spring:message code="sjp.error.page.description" /></h6>
			</div>
		</div>
			<!--
    			Failed URL: ${url}
    			Exception:  ${exception.message}
        		<c:forEach items="${exception.stackTrace}" var="ste">    
        			${ste} 
    			</c:forEach>
  			-->
		</div>
</section>
