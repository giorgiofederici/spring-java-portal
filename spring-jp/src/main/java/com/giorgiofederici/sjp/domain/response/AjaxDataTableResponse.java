package com.giorgiofederici.sjp.domain.response;

import java.util.List;

public class AjaxDataTableResponse {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	// private String[][] data;
	private List<?> data;

	public AjaxDataTableResponse() {
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	// public String[][] getData() {
	// return data;
	// }
	//
	// public void setData(String[][] data) {
	// this.data = data;
	// }

}
