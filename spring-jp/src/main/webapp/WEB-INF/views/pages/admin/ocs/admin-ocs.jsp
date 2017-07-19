<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<section class="row">
	<div class="col-12 col-sm-4">
		<h4><spring:message code="sjp.admin.ocs.management.customerAccounts.title" /></h4>
		<a href="<spring:url value="ocs/customer-accounts-management" /> "><img src="http://via.placeholder.com/200x200" class="img-fluid"></a>
	</div>
	<div class="col-12 col-sm-4">
		<h4><spring:message code="sjp.admin.ocs.management.category.title" /></h4>
		<a href="<spring:url value="ocs/categories-management" /> "><img src="http://via.placeholder.com/200x200" class="img-fluid"></a>
	</div>
	<div class="col-12 col-sm-4">
		<h4><spring:message code="sjp.admin.ocs.management.product.title" /></h4>
		<a href="<spring:url value="ocs/products-management" /> "><img src="http://via.placeholder.com/200x200" class="img-fluid"></a>
	</div>
</section>
