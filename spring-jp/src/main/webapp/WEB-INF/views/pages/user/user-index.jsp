<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<section class="row">
	<div class="col-12 col-sm-6">
		<div class="row">
			<div class="col-12">
				<h3>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.username" />
					</sec:authorize>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<c:choose>
					<c:when test="${empty userProfileImage} ">
						<img src="http://via.placeholder.com/150x150">
					</c:when>
					<c:otherwise>
						<img src="data:image/jpeg;base64,${userProfileImage}" width="150" height="150">
					</c:otherwise>
				</c:choose>				
			</div>
		</div>
		<div class="row user-profile-image-upload">
			<div class="col-12">
				<form:form method="POST" modelAttribute="userProfileImageForm" enctype="multipart/form-data">
					<!-- Product Image -->
					<div class="form-group">
				    	<form:input path="file" type="file" class="form-control-file" id="file" />
				    	<form:errors path="file" cssClass="text-danger" />
				  	</div>
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.user.index.upload" /></button>
				</form:form>
			</div>						
		</div>		
	</div>
	<div class="col-12 col-sm-6">
		<div class="row">
			<div class="col-12">
				<span><spring:message code="sjp.user.index.profile.username" /> <c:out value="${ user.username }" /></span>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<span><spring:message code="sjp.user.index.profile.email" /> <c:out value="${ user.email }" /></span>
			</div>
		</div>
	</div>
</section>
