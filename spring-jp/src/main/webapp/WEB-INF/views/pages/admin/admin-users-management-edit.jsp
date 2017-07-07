<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>

<section class="row">
	<div class="col-12">
		<div class="row">
		
			<div class="col-12 col-md-6 offset-md-3">
				<div class="row">
					<div class="col-12">
						<h3><spring:message code="sjp.admin.users.management.edit.title" /> <c:out value="${ userForm.username }" /></h3>	
					</div>
				</div>
								      
				<form:form modelAttribute="userForm" method="POST">
			  		<div class="form-group">
			    		<label for="username"><spring:message code="sjp.admin.users.management.edit.form.username" /> *</label>
			    		<form:input path="username" size="50" id="username" type="text" class="form-control" readonly="true" />
			    		<form:errors path="username" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="email"><spring:message code="sjp.admin.users.management.edit.form.email" /> *</label>
			    		<form:input path="email" size="50" id="email" type="text" class="form-control" />
			  			<form:errors path="email" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="enabled"><spring:message code="sjp.admin.users.management.edit.form.enabled" />:</label>
			    		<form:checkbox path="enabled" id="enabled" />
			  			<form:errors path="enabled" cssClass="text-danger" />
			  		</div>
			  
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.admin.users.management.edit.form.submit" /></button>
				</form:form >
			</div>
		</div>
	</div>

</section>