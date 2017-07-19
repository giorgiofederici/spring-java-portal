<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section class="row sjp-index-section">
	<div class="col-12">
		<h2><spring:message code="sjp.showcases.ocs.title" /></h2>
		<p class="text-justify"><spring:message code="sjp.showcases.ocs.description" /></p>
		<img src="/spring-jp/resources/images/showcases/online-cart-system/ocs-webflow.png" title="Online Cart System Diagram" class="img-fluid" />
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<h2>
			<a href="<spring:url value="/showcases/ocs/webflow/shopping" />" >
				<spring:message code="sjp.showcases.ocs.springWebFlow" />
				<img src="/spring-jp/resources/images/showcases/online-cart-system/spring-webflow-logo.png" title="Spring Web Flow" class="img-fluid" />			
			</a>
		</h2>
		
		<p class="text-justify"><spring:message code="sjp.showcases.ocs.springWebFlow.description" /></p>
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<h2>
			<a href="<spring:url value="/showcases/ocs/activiti-shopping" />" >
				<spring:message code="sjp.showcases.ocs.activity" />
				<img src="/spring-jp/resources/images/showcases/online-cart-system/activity-logo.png" title="Activity BPM 2.0" class="img-fluid" />			
			</a>
		</h2>		
		<p class="text-justify"><spring:message code="sjp.showcases.ocs.activity.description" /></p>
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<h2>
			<spring:message code="sjp.showcases.ocs.apachePluto" />
			<img src="/spring-jp/resources/images/showcases/online-cart-system/pluto-logo.png" title="Apache Pluto" class="img-fluid" />
		</h2>
		
		<p class="text-justify"><spring:message code="sjp.showcases.ocs.apachePluto.description" /></p>
	</div>
</section>