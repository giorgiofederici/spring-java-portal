<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${customerAccount == null}">
       <section class="row">
			<div class="col-12 col-sm-4">
				<h4><spring:message code="sjp.user.ocs.customer.account.title" /></h4>
				<a href="<spring:url value="/user/ocs/customer-account?user=${ username }" /> "><img src="http://via.placeholder.com/200x200" class="img-fluid"></a>
			</div>
		</section>
    </c:when>    
    <c:otherwise>
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
				<h4><spring:message code="sjp.admin.ocs.management.catalog.title" /></h4>
				<a href="<spring:url value="ocs/catalog-management" /> "><img src="http://via.placeholder.com/200x200" class="img-fluid"></a>
			</div>
		</section>
    </c:otherwise>
</c:choose>


