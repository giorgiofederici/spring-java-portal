package com.giorgiofederici.sjp.domain.response;

import java.util.List;

import org.springframework.social.twitter.api.SearchResults;

public class AjaxTwitterSearchResponseBody {

	private String msg;
	private SearchResults searchRresults;
	private int tweetsCount;
	private int retweetsCount;
	private String firstCreatedAt;
	private String lastCreatedAt;
	private int usersCount;

	private List<JFreeChartResponse> jfreeChartResponseList;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SearchResults getSearchRresults() {
		return searchRresults;
	}

	public void setSearchRresults(SearchResults searchRresults) {
		this.searchRresults = searchRresults;
	}

	public int getTweetsCount() {
		return tweetsCount;
	}

	public void setTweetsCount(int tweetsCount) {
		this.tweetsCount = tweetsCount;
	}

	public int getRetweetsCount() {
		return retweetsCount;
	}

	public void setRetweetsCount(int retweetsCount) {
		this.retweetsCount = retweetsCount;
	}

	public String getFirstCreatedAt() {
		return firstCreatedAt;
	}

	public void setFirstCreatedAt(String firstCreatedAt) {
		this.firstCreatedAt = firstCreatedAt;
	}

	public String getLastCreatedAt() {
		return lastCreatedAt;
	}

	public void setLastCreatedAt(String lastCreatedAt) {
		this.lastCreatedAt = lastCreatedAt;
	}

	public int getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}

	public List<JFreeChartResponse> getJfreeChartResponseList() {
		return jfreeChartResponseList;
	}

	public void setJfreeChartResponseList(List<JFreeChartResponse> jfreeChartResponseList) {
		this.jfreeChartResponseList = jfreeChartResponseList;
	}

}
