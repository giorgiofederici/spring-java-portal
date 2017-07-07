<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>


<section class="row">
	<div class="col-12 text-center">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.user.twitter.connect.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p><spring:message code="sjp.user.twitter.connect.description" /></p>
				<form:form action="/spring-jp/connect/twitter" method="POST">
					<button type="submit" class="btn btn-outline-danger"><spring:message code="sjp.user.twitter.connect.form.submit" /></button>
				</form:form>
			</div>
		</div>
	</div>
</section>
