
/* Twitter Search START */

$(document).ready(function () {

    $("#twitterSearchForm").submit(function (event) {
    	
        //stop submit the form, we will post it manually.
        event.preventDefault();

        twitterSearchCleanHTML();
        
        fire_ajax_twitter_search_submit();

    });

});

function fire_ajax_twitter_search_submit() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
    var search = {};
    search["searchQuery"] = $("#twitter-search-text").val();
    
    $("#twitter-search-btn").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "twitter-search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function(xhr) {
			  xhr.setRequestHeader(header, token);
		  },
        success: function (data) {
            
            twitterSearchSuccess(data);
            console.log(data);
            $("#twitter-search-btn").prop("disabled", false);
            
            

        },
        error: function (e) {

            console.log("ERROR : ", e);
            $("#twitter-search-btn").prop("disabled", false);

        }
    });

}


function twitterSearchCleanHTML(){
	
	$('#tweets').remove();
	
	$('#tweets-count').empty();
	$('#retweets-count').empty();
	$('#users-count').empty();
	
	$('#twitter-search-jfree-3dpie').empty();
	
}

function twitterSearchSuccess(data){	
	
	$('#twitter-search-result').append('<table id="tweets" class="table"><thead><tr><th>Tweets</th></tr></thead><tbody></tbody></table>');
	
	$('#tweets-count').text(data.tweetsCount);
	$('#retweets-count').text(data.retweetsCount);
	$('#users-count').text(data.usersCount);
	
	$('#tweets').append(
		    $.map(data.searchRresults.tweets, function (ignore, index) {
		    	return '<tr><td><img class="twitter-profile-img" src="' + data.searchRresults.tweets[index].profileImageUrl + '" />' + data.searchRresults.tweets[index].text + '</td></tr>';
		}).join());	
	
	
	$('#twitter-search-jfree-3dpie').append('<img src="' + data.jfreeChartResponseList[0].chart + '" usemap="#twitter-search-jfree-3dpie-map" />');
	$('#twitter-search-jfree-3dpie').append(data.jfreeChartResponseList[0].mapChart);
}

/* Twitter Search END */
