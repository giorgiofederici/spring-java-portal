package com.giorgiofederici.sjp.domain.response;

public class JFreeChartResponse {

	private String chart;
	private String mapChart;

	public JFreeChartResponse(String chart, String mapChart) {
		this.chart = chart;
		this.mapChart = mapChart;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	public String getMapChart() {
		return mapChart;
	}

	public void setMapChart(String mapChart) {
		this.mapChart = mapChart;
	}

}
