package com.springboot.study.handler.ex;

import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationApiException1 extends RuntimeException{
	private static final long seralVersionUID = 1l;
	
	private Map<String, String> errorMap;
	
	public CustomValidationApiException1(String message) {
		super(message);
	}
	
	public CustomValidationApiException1(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
}
