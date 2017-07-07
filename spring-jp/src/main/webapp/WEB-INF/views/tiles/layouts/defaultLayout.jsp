<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Spring Java Portal (SJP)</title>
	<meta name="description" content="Spring Java Portal is a project created by Giorgio Federici to group all the Java knowledge together. The aim of the Spring Java Portal is to be a showcase and reference for the future.">
	
	<!-- TODO: Create a favicon -->
	<link rel="icon" href="/spring-jp/resources/favicon.ico" type="image/x-icon">
	
	<meta name="author" content="Giorgio Federici">
	<meta name="copyright" content="Giorgio Federici">

	<meta name="date.created" content="14-06-2017">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,400">
    <link rel="stylesheet" href="/spring-jp/resources/css/style.css?version=1.1">
    
    <script src="https://use.fontawesome.com/cb4ee38d2b.js"></script>
    
</head>
<body>
	<div class="sjp-wrapper">
		
		<div class="container sjp-container">
	
			<header id="header" class="row default-header">
	            <tiles:insertAttribute name="header" />
	        </header>
	        
	        
	         <section class="row navigation-main">
	        	<tiles:insertAttribute name="navigation-main" />	
	        </section>     
	        
	        
			<tiles:insertAttribute name="content" />
	        
	        <hr>
	        
	        <footer id="footer" class="row">
	        	<tiles:insertAttribute name="footer" />
	        </footer>     
           
		</div>
		
	</div>	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

</body>
</html>