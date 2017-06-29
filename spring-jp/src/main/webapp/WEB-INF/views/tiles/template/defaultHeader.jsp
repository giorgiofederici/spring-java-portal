<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- Logo and Main Title Column -->
<div class="col-12 col-md-6">
	<div class="row">
		<div class="col-12">
			<spring:url value="/index" var="index" />
			<h1>
				<a href="${ index }" title="Spring Java Portal Homepage"> <spring:message
						code="sjp.default.header.title" />
				</a>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<h6>
				<spring:message code="sjp.default.header.subtitle" />
			</h6>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<a href="?lang=it"
				title="<spring:message code="sjp.default.header.italian" />"><img
				alt="Italian flag" src="/spring-jp/resources/images/italian-flag.jpg"></a> <a
				href="?lang=en" title="<spring:message code="sjp.default.header.english" />"><img
				alt="English flag" src="/spring-jp/resources/images/english-flag.jpg"></a>
		</div>
	</div>
</div>
<!-- Top Right Navigation -->
<div class="col-12 col-md-6">
	<div class="row pull-right">
		<div class="col-12">
			<nav class="navbar navbar-toggleable-sm">

				<!-- Responsive Menu -->
				<button class="navbar-toggler navbar-toggler-right" type="button"
					data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse navbar-top-content"
					id="navbarTopSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item">
				        	<a class="nav-link" href="<spring:url value="/index" />" ><spring:message code="sjp.default.header.nav.top.home" /></a>
				      	</li>
				      	<li class="nav-item">
				        	<a class="nav-link" href="<spring:url value="/signin" />" ><spring:message code="sjp.default.header.nav.top.signin" /></a>
				      	</li>
				      	<li class="nav-item">
				        	<a class="nav-link" href="<spring:url value="/signup" />" ><spring:message code="sjp.default.header.nav.top.signup" /></a>
				      	</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>
