package com.forum.util;

public class ResultSet {

	//状态码  0：正确		1：错误
	private int stateCode;
	
	//错误信息
	private String message;

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
