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
						<h1><spring:message code="sjp.user.ocs.customer.account.title" /></h1>	
					</div>
				</div>
								      
				<form:form modelAttribute="customerAccountForm" method="POST">
			  		<div class="form-group">
			    		<label for="username"><spring:message code="sjp.user.ocs.customer.account.form.username" /> *</label>
			    		<form:input path="username" size="50" id="username" type="text" class="form-control" readonly="true" />
			    		<form:errors path="username" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="firstName"><spring:message code="sjp.user.ocs.customer.account.form.firstName" /> *</label>
			    		<form:input path="firstName" size="45" id="firstName" type="text" class="form-control" />
			  			<form:errors path="firstName" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="lastName"><spring:message code="sjp.user.ocs.customer.account.form.lastName" /> *</label>
			    		<form:input path="lastName" size="45" id="lastName" type="lastName" class="form-control" />
			  			<form:errors path="lastName" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="midName"><spring:message code="sjp.user.ocs.customer.account.form.midName" /> *</label>
			    		<form:input path="midName" size="45" id="midName" type="midName" class="form-control" />
			  			<form:errors path="midName" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="email"><spring:message code="sjp.user.ocs.customer.account.form.email" /> *</label>
			    		<form:input path="email" size="45" id="email" type="email" class="form-control" />
			  			<form:errors path="email" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="mobile"><spring:message code="sjp.user.ocs.customer.account.form.mobile" /> *</label>
			    		<form:input path="mobile" size="45" id="mobile" type="mobile" class="form-control" />
			  			<form:errors path="mobile" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="address"><spring:message code="sjp.user.ocs.customer.account.form.address" /> *</label>
			    		<form:input path="address" size="255" id="address" type="address" class="form-control" />
			  			<form:errors path="address" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="birthDate"><spring:message code="sjp.user.ocs.customer.account.form.birthDate" /> *</label>
			    		<form:input path="birthDate" id="birthDate" type="date" class="form-control" size="10" />
			    		<form:errors path="birthDate" cssClass="text-danger" />
			  		</div>			  		
			  
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.user.ocs.customer.account.form.submit" /></button>
				</form:form >
			</div>
		</div>
	</div>

</section>
