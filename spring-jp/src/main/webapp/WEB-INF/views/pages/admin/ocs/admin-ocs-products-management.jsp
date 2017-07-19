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
						<h3><spring:message code="sjp.admin.ocs.products.management.addProduct" /></h3>	
					</div>
				</div>

				<form:form modelAttribute="productForm" method="POST">
			  		<div class="form-group">
			    		<label for="name"><spring:message code="sjp.admin.ocs.products.management.addProduct.name" /> *</label>
			    		<form:input path="name" size="100" id="name" type="text" class="form-control" />
			    		<form:errors path="name" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="categoryId"><spring:message code="sjp.admin.ocs.products.management.addProduct.category" /> *</label>
			    		<form:select path="categoryId" id="categoryId" class="form-control"  multiple="false">
			    			<form:options items="${ categories }" itemLabel="name" itemValue="id" />
			    		</form:select>
			    		<form:errors path="categoryId" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="price"><spring:message code="sjp.admin.ocs.products.management.addProduct.price" /> *</label>
			    		<form:input path="price" id="price" type="number" class="form-control" step="0.01" />
			    		<form:errors path="price" cssClass="text-danger" />
			  		</div>
			  		<div class="form-group">
			    		<label for="stock"><spring:message code="sjp.admin.ocs.products.management.addProduct.stock" /> *</label>
			    		<form:input path="stock" id="stock" type="number" class="form-control" />
			    		<form:errors path="stock" cssClass="text-danger" />
			  		</div>			  
			  		<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.admin.ocs.products.management.addProduct.submit" /></button>
				</form:form >
			</div>
		</div>
	</div>

</section>
<hr>
<section class="row">
	<div class="col-12">
		<table id="ocs-products-management-table" class="table table-striped table-bordered" width="100%">
			<thead>
	            <tr>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.id" /></th>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.name" /></th>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.category" /></th>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.price" /></th>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.stock" /></th>
	                <th><spring:message code="sjp.admin.ocs.products.management.table.actions" /></th>
	            </tr>
	        </thead>
		</table>


	</div>
</section>
