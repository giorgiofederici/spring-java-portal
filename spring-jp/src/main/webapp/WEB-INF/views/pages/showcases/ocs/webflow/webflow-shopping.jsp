<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>


<section class="row" ng-app="ocsApp" ng-controller="ocsController" ng-init="webflowShoppingInit()">
	<div class="col-12">
		<section class="row webflow-shopping-cart-top">
			<div class="col-10">
			</div>
			<div class="col-2">
				<div class="float-right" >
					<a href="<spring:url value="/showcases/ocs/webflow/cart" />" class="btn btn-default" title="View Cart"> 
						<span id="webflow-shopping-cart-items-number" class="sjp-shopping-cart">0</span>
		 				<i class="fa fa-shopping-cart fa-2x sjp-fa-cart" aria-hidden="true"></i>
		 			</a>
				</div>
			</div>
		</section>
		
		<section class="row" >
			<div class="col-12">
				<div class="card-deck">
					<c:forEach var="product" items="${ products }">
						<div class="card">
				    		<img class="card-img-top" src="http://via.placeholder.com/200x200" alt="Card image cap">
				   			<div class="card-block">
				    			<h4 class="card-title"><c:out value="${ product.name }" /></h4>
				      			<p class="card-text"><span><c:out value="${ product.price }" /> &euro;</span></p>
				    		</div>
				    		<div class="card-footer sjp-shopping-item-add" >
								<a href="#" class="btn btn-danger btn-large"  ng-click="addToCart('${product.id}')"> 
		      						<i class="fa fa-shopping-cart" aria-hidden="true"></i> Order Now 
		       					</a>
				   			</div>
				  		</div>
				  	</c:forEach>
				</div>
			</div>
		</section>	
	</div>
</section>
