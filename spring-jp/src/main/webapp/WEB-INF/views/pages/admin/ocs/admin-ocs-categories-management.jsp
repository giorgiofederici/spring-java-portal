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
						<h3><spring:message code="sjp.admin.ocs.categories.management.addCategory" /></h3>	
					</div>
				</div>
								      
				<form:form modelAttribute="categoryForm" method="POST">
			  		<div class="form-group">
			    		<label for="name"><spring:message code="sjp.admin.ocs.categories.management.addCategory.name" /> *</label>
			    		<form:input path="name" size="100" id="name" type="text" class="form-control" />
			    		<form:errors path="name" cssClass="text-danger" />
			  		</div>			  
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.admin.ocs.categories.management.addCategory.submit" /></button>
				</form:form >
			</div>
		</div>
	</div>

</section>
<hr>
<section class="row">
	<div class="col-12">
		<table id="ocs-categories-management-table" class="table table-striped table-bordered" width="100%">
			<thead>
	            <tr>
	                <th><spring:message code="sjp.admin.ocs.categories.management.table.id" /></th>
	                <th><spring:message code="sjp.admin.ocs.categories.management.table.name" /></th>
	                <th><spring:message code="sjp.admin.ocs.categories.management.table.actions" /></th>
	            </tr>
	        </thead>
		</table>


	</div>
</section>
