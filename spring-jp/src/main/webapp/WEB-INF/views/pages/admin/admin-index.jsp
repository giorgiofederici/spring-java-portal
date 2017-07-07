<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<section class="row">
	<div class="col-12 col-sm-4">
		<div class="row">
			<div class="col-12">
				<h3>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.username" />
					</sec:authorize>
				</h3>
			</div>
			<div class="col-12">
				<img src="http://via.placeholder.com/150x150">
			</div>
		</div>
		
	</div>
</section>
