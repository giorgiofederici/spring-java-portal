package com.giorgiofederici.sjp.controller.user.social;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.giorgiofederici.sjp.domain.form.TwitterSearchCriteriaForm;
import com.giorgiofederici.sjp.domain.response.AjaxTwitterSearchResponseBody;
import com.giorgiofederici.sjp.domain.response.JFreeChartResponse;

@RestController
public class TwitterSearchController {

	private Twitter twitter;

	private ConnectionRepository connectionRepository;

	@Inject
	public TwitterSearchController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(value = "/user/social/twitter-search", method = RequestMethod.POST)
	public ResponseEntity<?> submitTwitterSearch(@Valid @RequestBody TwitterSearchCriteriaForm search, Errors errors,
			HttpServletRequest request) throws ServletException, IOException {

		AjaxTwitterSearchResponseBody ajaxResponse = new AjaxTwitterSearchResponseBody();

		if (errors.hasErrors()) {

			ajaxResponse.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

			return ResponseEntity.badRequest().body(ajaxResponse);

		}

		SearchResults searchResults = this.twitter.searchOperations().search(search.getSearchQuery());

		if (searchResults == null) {
			ajaxResponse.setMsg("no search results found!");
		} else {
			ajaxResponse.setMsg("success");

			List<Tweet> tweets = searchResults.getTweets();

			analyseTweets(tweets, ajaxResponse, request);
		}
		ajaxResponse.setSearchRresults(searchResults);

		return ResponseEntity.ok(ajaxResponse);

	}

	private void analyseTweets(List<Tweet> tweets, AjaxTwitterSearchResponseBody response, HttpServletRequest request)
			throws ServletException, IOException {

		if (tweets != null) {

			Set<TwitterProfile> twitterProfiles = new HashSet<TwitterProfile>();

			response.setTweetsCount(tweets.size());

			int retweetsCount = 0;

			for (Tweet tweet : tweets) {
				if (tweet.isRetweet()) {
					retweetsCount++;
				}

				twitterProfiles.add(tweet.getUser());

			}

			response.setUsersCount(twitterProfiles.size());
			response.setRetweetsCount(retweetsCount);

			List<JFreeChartResponse> jfreeCharts = new ArrayList<JFreeChartResponse>();
			jfreeCharts.add(createTweetsType3DPieChart(tweets.size(), retweetsCount, request));

			response.setJfreeChartResponseList(jfreeCharts);
		}
	}

	private JFreeChartResponse createTweetsType3DPieChart(int tweetsCount, int retweetsCount,
			HttpServletRequest request) throws ServletException, IOException {

		final DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Tweets", tweetsCount - retweetsCount);
		dataset.setValue("Retweets", retweetsCount);

		final JFreeChart chart = ChartFactory.createPieChart3D("Tweets Type", dataset, true, true, false);

		chart.setBackgroundPaint(Color.white);
		final TextTitle subtitle = new TextTitle(" The chart shows the classification of tweets.");
		subtitle.setFont(new Font("Calibri", Font.PLAIN, 12));

		chart.addSubtitle(subtitle);

		ChartRenderingInfo info = null;

		info = new ChartRenderingInfo(new StandardEntityCollection());
		HttpSession session = request.getSession();
		String filename = ServletUtilities.saveChartAsPNG(chart, 700, 300, info, session);
		String chartMap = ChartUtilities.getImageMap("twitter-search-jfree-3dpie-map", info);
		String chartPath = request.getContextPath() + "/jfree-chart?filename=" + filename;
		return new JFreeChartResponse(chartPath, chartMap);
	}

}
