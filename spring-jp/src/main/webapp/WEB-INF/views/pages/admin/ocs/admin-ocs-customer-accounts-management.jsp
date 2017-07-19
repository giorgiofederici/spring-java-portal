<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>


<section class="row">
	<div class="col-12">
		<table id="ocs-customer-accounts-management-table" class="table table-striped table-bordered" width="100%">
			<thead>
	            <tr>
	                <th><spring:message code="sjp.admin.ocs.customer.accounts.management.table.username" /></th>
	                <th><spring:message code="sjp.admin.ocs.customer.accounts.management.table.firstName" /></th>
	                <th><spring:message code="sjp.admin.ocs.customer.accounts.management.table.lastName" /></th>
	                <th><spring:message code="sjp.admin.ocs.customer.accounts.management.table.email" /></th>
	                <th><spring:message code="sjp.admin.ocs.customer.accounts.management.table.actions" /></th>
	            </tr>
	        </thead>
		</table>


	</div>
</section>
