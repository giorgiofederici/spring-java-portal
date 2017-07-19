<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="row" ng-app="ocsApp" ng-controller="ocsController" ng-init="initCartId('${cartId}')">
	<div class="col-12">
		<section class="row webflow-shopping-cart-top">
			<div class="col-12">
				<a href="#" class="btn btn-danger pull-left" ng-click="clearCart()">
       				<i class="fa fa-trash-o" aria-hidden="true"></i>Clear Cart 
				</a>
				<a href="<spring:url value="/ocs?cartId=${cartId}"/>" class="btn btn-success pull-right">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i> Check out 
				</a>
			</div>
		</section>
		<section class="row">
			<div class="col-12">
				<table class="table table-hover"> 
					<tr> 
						<th>Product</th> 
		   				<th>Unit price</th> 
		   				<th>Qauntity</th> 
		   				<th>Price</th> 
		   				<th>Action</th> 
					</tr> 
		                  <tr ng-repeat="item in cart.cartItems"> 
		                     <td>{{item.product.name}}</td> 
		                     <td>{{item.product.price}}</td> 
		                     <td>{{item.quantity}}</td> 
		                    <td>{{item.totalPrice}}</td> 
		                    <td>
		                    	<a href="#" class="label label-danger" ng-click="removeFromCart(item.product.id)">
		                   			<i class="fa fa-times" aria-hidden="true"></i>
		                    	</a>
		                    </td> 
						</tr> 
		                 <tr> 
		                 	<th></th> 
		                    <th></th> 
		                    <th>Grand Total</th> 
		                    <th>{{cart.grandTotal}}</th> 
		                    <th></th> 
		                 </tr> 
				</table> 
          
				<a href="<spring:url value="/showcases/ocs/webflow/shopping" />" class="btn btn-default"> 
					<i class="fa fa-arrow-left" aria-hidden="true"></i> Continue shopping 
				</a> 
			</div>
		</section>
	</div>
</section> 