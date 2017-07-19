<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="wrap">
	<div id="accordian">
		<div class="step" id="step3">
			<div class="number">
				<span>4</span>
			</div>
			<div class="title">
				<h1>Shipping Information</h1>
			</div>
			<div class="modify">
				<i class="fa fa-plus-circle"></i>
			</div>
		</div>
		<div class="content" id="shipping">
			<form>
				<div>
					<input type="radio" id="shipping_1" value="1"/><label for="shipping_1"> Standard Shipping <span class="price">$4.00</span></label>
        </div>
				<div>		
					<input type="radio" id="shipping_2" value="2"/><label for="shipping_2"> Express Shipping <span class="price">$8.00</span></label>
				</div>
				<div>		
					<input type="radio" id="shipping_3" value="3"/><label for="shipping_3"> Overnight Shipping <span class="price">$12.00</span></label>
				</div>
			</form>

			<a class="continue" href="<spring:url value="/showcases/ocs/activiti-payment" />">Continue</a>
		</div>

</div>
</div>