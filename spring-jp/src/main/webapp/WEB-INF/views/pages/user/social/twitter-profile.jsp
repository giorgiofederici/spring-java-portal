<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="row">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h1 class="twitter-text-color">
					<img class="twitter-profile-img" src="${twitterProfile.profileImageUrl}">
					<c:out value="${twitterProfile.name} (@${twitterProfile.screenName})" />
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<table class="table twitter-profile-table">
					<thead>
				    	<tr>
				      		<th><spring:message code="sjp.user.twitter.profile.tweets" /></th>
				      		<th><spring:message code="sjp.user.twitter.profile.followers" /></th>
				      		<th><spring:message code="sjp.user.twitter.profile.following" /></th>
				    	</tr>
				  	</thead>
				  	<tbody>
				    	<tr>
				      		<td><c:out value="${twitterProfile.statusesCount}" /></td>
				      		<td><c:out value="${twitterProfile.followersCount}" /></td>
				      		<td><c:out value="${twitterProfile.friendsCount}" /></td>
			    		</tr>
				  	</tbody>
				</table>					
			</div>
		</div>
	</div>
</section>
<section class="row">
	<div class="col-12 twitter-search-main">
		<div class="row twitter-search-container">
			<div class="col-md-6">
				<form id="twitterSearchForm" action="/spring-jp/user/social/twitter-search" method="POST" class="form-inline">
					<input id="twitter-search-text" class="form-control mr-sm-2" type="text" name="twitter-search-text" placeholder="Search on Twitter...">
      				<button id="twitter-search-btn" class="btn btn-outline-info" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>      				
				</form>
  			</div>
  		</div>
		<ul class="nav nav-tabs">
			<li class="nav-item">
    			<a class="nav-link active" data-toggle="tab" href="#twitterSearchDashboardTab"><spring:message code="sjp.user.twitter.profile.search.dashboard"/></a>
			</li>
			<li class="nav-item">
    			<a class="nav-link" data-toggle="tab" href="#jfreeChartsTab"> JFree Charts</a>
			</li>
			<li class="nav-item">
    			<a class="nav-link" data-toggle="tab" href="#link">Link</a>
			</li>
		</ul>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="twitterSearchDashboardTab">
    			<div id="twitter-search-summary" class="row">
		  			<div  class="col-12">
			  			<table class="table twitter-profile-table">
							<thead>
						    	<tr>
						      		<th><spring:message code="sjp.user.twitter.profile.search.tweets" /></th>
						      		<th><spring:message code="sjp.user.twitter.profile.search.retweets" /></th>
						      		<th><spring:message code="sjp.user.twitter.profile.search.users" /></th>
						    	</tr>
						  	</thead>
						  	<tbody>
						    	<tr>
						      		<td><span id="tweets-count">0</span></td>
						      		<td><span id="retweets-count">0</span></td>
						      		<td><span id="users-count">0</span></td>
					    		</tr>
						  	</tbody>
						</table>	
		  			</div>
		  		</div>
		  		<div class="row">
		  			<div id="twitter-search-result" class="col-12">
		  			</div>
		  		</div>
			</div>
           	<div role="tabpanel" class="tab-pane fade in" id="jfreeChartsTab">
               	<div class="row twitter-search-jfreechart">
               		<div id="twitter-search-jfree-3dpie" class="col-12 "></div>
               	</div>
           	</div>
            <div role="tabpanel" class="tab-pane fade in" id="link">
               <ul class="list-group media-list media-list-stream">
                   <p>Link</p>
               </ul>
           </div>
		</div>
		
	</div>
</section>
