<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="row">
	<div class="col-12 text-center">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.user.twitter.connected.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p><spring:message code="sjp.user.twitter.connected.description" /></p>
				<a href="/spring-jp/user/social/twitter-profile"><spring:message code="sjp.user.twitter.connected.accessProfile" /></a>
			</div>
		</div>
	</div>
</section>