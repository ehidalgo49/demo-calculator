package com.calculator.demo.modelo;

public class Header {

	private String code;
	
	private String message;

	public Header(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public Header() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
