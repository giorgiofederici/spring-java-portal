<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="shoppingCartProducts" value="${sessionScope.shoppingCartDto.products}" />

<section class="row sjp-shopping-cart-section">
	<div class="col-10">
		<div class="sjp-shopping-cart-selected-products">
			<div class="card-inverse mb-3 sjp-shopping-cart-selected-products-card">
				<c:forEach var="selectedProduct" items="${ shoppingCartProducts }">	    				
					<blockquote class="card-blockquote">
      					<span>${ selectedProduct.name }</span>
      					<form:form modelAttribute="shoppingCartDto" action="/spring-jp/showcases/ocs/activiti-shopping/remove/${ selectedProduct.id }" method="POST" cssClass="inline">
							<button type="submit" class="sjp-shopping-cart-remo-link-button" title="Remove Product">
								<i class="fa fa-trash-o fa-left" aria-hidden="true"></i>
							</button>
			   			</form:form>
   					</blockquote>
  				</c:forEach>
			</div>			
		</div>
	</div>
	<div class="col-2">
		<div id="sjp-shopping-cart-activiti" class="float-right" >
			<span class="sjp-shopping-cart"><c:out value="${fn:length(shoppingCartProducts)}"/></span>
			<i class="fa fa-shopping-cart fa-2x sjp-fa-cart" aria-hidden="true"></i>
		</div>
	</div>
</section>

<!-- <section class="row"> -->
<%-- 	<c:forEach var="product" items="${ products }"> --%>
<!-- 	<div class="col-12 col-sm-4"> -->
<!-- 		<div class="sjp-shopping-item"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-10"> -->
<%-- 					<h4><c:out value="${ product.name }" /></h4> --%>
<!-- 				</div> -->
<!-- 				<div class="col-2 sjp-shopping-item-add"> -->
<%-- 					<form:form modelAttribute="shoppingCartDto" action="/spring-jp/showcases/ocs/activiti-shopping/add/${ product.id }" method="POST" cssClass="inline"> --%>
<%-- 						<button type="submit" class="link-button" title="<spring:message code="sjp.user.header.nav.top.logout" />"> --%>
<!-- 							<i class="fa fa-plus-circle" aria-hidden="true"></i> -->
<!-- 						</button> -->
<%-- 			   		</form:form> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-12"> -->
<%-- 					<p><span><c:out value="${ product.price }" /> &euro;</span></p> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-12 text-center sjp-shopping-item-image"> -->
<!-- 					<img src="http://via.placeholder.com/150x150" class="img-fluid"> -->
<!-- 				</div> -->
<!-- 			</div>					 -->
<!-- 	   	</div> -->
<!-- 	</div> -->
<%-- 	</c:forEach> --%>
<!-- </section> -->
<section class="row">
	<div class="col-12">
		<div class="card-deck">
			<c:forEach var="product" items="${ products }">
				<div class="card">
		    		<img class="card-img-top" src="http://via.placeholder.com/200x200" alt="Card image cap">
		   			<div class="card-block">
		    			<h4 class="card-title"><c:out value="${ product.name }" /></h4>
		      			<p class="card-text"><span><c:out value="${ product.price }" /> &euro;</span></p>
		    		</div>
		    		<div class="card-footer sjp-shopping-item-add">
						<form:form modelAttribute="shoppingCartDto" action="/spring-jp/showcases/ocs/activiti-shopping/add/${ product.id }" method="POST" cssClass="inline">
							<button type="submit" class="link-button" title="Add Product">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>
							</button>
			   			</form:form>
		   			</div>
		  		</div>
		  	</c:forEach>
		</div>
	</div>
</section>

<div id="complete">
						<a class="big_button" id="complete" href="<spring:url value="/showcases/ocs/activiti-billing" />">Complete Order</a> <span
							class="sub">By selecting this button you agree to the
							purchase and subsequent payment for this order.</span>
					</div>

<div id="wrap">
		<div id="accordian">
			<div class="step" id="step5">
				<div class="number">
					<span>1</span>
				</div>
				<div class="title">
					<h1>Finalize Order</h1>
				</div>
				<div class="modify">
					<i class="fa fa-plus-circle"></i>
				</div>
			</div>
			<div class="content" id="final_products">
				<div class="left" id="ordered">
					<div class="products">
						<div class="product_image">
							<img src="${ iconImg }" />
						</div>
						<div class="product_details">
							<span class="product_name">Candy</span> <span
								class="quantity">10</span> <span class="price">P45.00</span>
						</div>
					</div>
					<div class="products">
						<div class="product_image">
							<img src="${ iconImg }" />
						</div>
						<div class="product_details">
							<span class="product_name">Paper</span> <span
								class="quantity">25</span> <span class="price">P85.00</span>
						</div>
					</div>
					<div class="products">
						<div class="product_image">
							<img src="${ iconImg }" />
						</div>
						<div class="product_details">
							<span class="product_name">Frying Pans</span> <span
								class="quantity">50</span> <span class="price">P145.00</span>
						</div>
					</div>
			
				</div>
				
				<div class="right" id="reviewed">
					<div class="billing">
						<span class="title">Billing:</span>
						<div class="address_reviewed">
							<span class="name">John Smith</span> <span class="address">123
								Main Street</span> <span class="location">Everytown, USA, 12345</span>
							<span class="phone">(123)867-5309</span>
						</div>
					</div>
					<div class="shipping">
						<span class="title">Shipping:</span>
						<div class="address_reviewed">
							<span class="name">John Smith</span> <span class="address">123
								Main Street</span> <span class="location">Everytown, USA, 12345</span>
							<span class="phone">(123)867-5309</span>
						</div>
					</div>
					<div class="payment">
						<span class="title">Payment:</span>
						<div class="payment_reviewed">
							<span class="method">Visa</span> <span class="number_hidden">xxxx-xxxx-xxxx-1111</span>
						</div>
					</div>
					<spring:url value="/ocs/billing.html"	var="nextPage" />
					
					<div id="complete">
						<a class="big_button" id="complete" href="${ nextPage }">Complete Order</a> <span
							class="sub">By selecting this button you agree to the
							purchase and subsequent payment for this order.</span>
					</div>
					
				</div>
						
			</div>

		</div>
	</div>