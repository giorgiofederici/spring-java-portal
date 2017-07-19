var ocsApp = angular.module('ocsApp', []);

ocsApp.controller('ocsController', function($scope, $http){
	
	$scope.csrfToken = $("meta[name='_csrf']").attr("content");
	$scope.csrfHeader = $("meta[name='_csrf_header']").attr("content");
	
	$http.defaults.headers.post[$scope.csrfHeader]=$scope.csrfToken;
	
	$scope.refreshCart = function(cartId){
		$http.get('/spring-jp/showcases/ocs/rest/cart/' + $scope.cartId).success(function(data){
			$scope.cart = data;
		});
	};
	
	$scope.clearCart = function(){
		$http.post('/spring-jp/showcases/ocs/rest/cart/delete/' + $scope.cartId).success(function(data){
			$scope.refreshCart($scope.cartId);
		});
	};
	
	$scope.initCartId = function(cartId){
		$scope.cartId = cartId;
		$scope.refreshCart($scope.cartId);
	};
	
	$scope.addToCart = function(productId){
		$http.post('/spring-jp/showcases/ocs/rest/cart/add/product/' + productId).success(function(data){
			$scope.countCartItems();
		});
	};
	
	$scope.removeFromCart = function(productId){
		$http.post('/spring-jp/showcases/ocs/rest/cart/remove/product/' + productId).success(function(data){
			$scope.refreshCart($scope.cartId);
		});
	};
	
	$scope.webflowShoppingInit = function(){
		$scope.countCartItems();
	};
	
	$scope.countCartItems = function(){
		$http.get('/spring-jp/showcases/ocs/rest/cart/count').success(function(data){
			$scope.webFlowShoppingCartItemsNumber = $('#webflow-shopping-cart-items-number');
			$scope.webFlowShoppingCartItemsNumber.empty();
			$scope.webFlowShoppingCartItemsNumber.text(data);
		});
	};
});