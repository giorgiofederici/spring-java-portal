<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="row">
	<div class="col-12">
		<div class="row">
		
			<div class="col-12 col-md-6 offset-md-3">
				<div class="row">
					<div class="col-12">
						<h1><spring:message code="sjp.signup.title" /></h1>	
					</div>
				</div>
								      
				<form:form modelAttribute="signUpForm" method="POST">
			  		<div class="form-group">
			    		<label for="username"><spring:message code="sjp.signup.form.username" /> *</label>
			    		<form:input path="username" size="50" id="username" type="text" class="form-control" />
			    		<form:errors path="username" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="email"><spring:message code="sjp.signup.form.email" /> *</label>
			    		<form:input path="email" size="50" id="email" type="text" class="form-control" />
			  			<form:errors path="email" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="password"><spring:message code="sjp.signup.form.password" /> *</label>
			    		<form:input path="password" size="50" id="password" type="password" class="form-control" />
			  			<form:errors path="password" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="matchingPassword"><spring:message code="sjp.signup.form.confirmPassword" /> *</label>
			    		<form:input path="matchingPassword" size="50" id="matchingPassword" type="password" class="form-control" />
			    		<form:errors path="matchingPassword" cssClass="text-danger" />
			  		</div>
			  
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.signup.form.submit" /></button>
				</form:form >
			</div>
		</div>
	</div>

</section>
