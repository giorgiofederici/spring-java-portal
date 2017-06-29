<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="technologies" class="row">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h2><spring:message code="sjp.techs.title"/></h2>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<ul>
					<li>
						<a href="<spring:url value="/technologies/database" />" ><spring:message code="sjp.techs.db" /></a>
					</li>
					<li>
						<a href="<spring:url value="/technologies/hibernate" />" ><spring:message code="sjp.techs.hibernate" /></a>
					</li>
				</ul>			
			</div>			
		</div>
	</div>
</section>
