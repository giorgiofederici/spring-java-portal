package com.giorgiofederici.sjp.domain.form;

import java.io.Serializable;

public class DataTableForm implements Serializable {

	private static final long serialVersionUID = 4143099315761514986L;

	private int draw;
	private int start;
	private int length;

	// add search params

	// add order params

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
