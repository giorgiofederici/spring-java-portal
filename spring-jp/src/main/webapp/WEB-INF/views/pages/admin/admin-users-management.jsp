<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<section class="row">
	<div class="col-12">
		<table id="admin-users-management-table" class="table table-striped table-bordered" width="100%">
			<thead>
	            <tr>
	                <th><spring:message code="sjp.admin.users.management.table.username" /></th>
	                <th><spring:message code="sjp.admin.users.management.table.email" /></th>
	                <th><spring:message code="sjp.admin.users.management.table.enabled" /></th>
	                <th><spring:message code="sjp.admin.users.management.table.actions" /></th>
	            </tr>
	        </thead>
		</table>


	</div>
</section>
