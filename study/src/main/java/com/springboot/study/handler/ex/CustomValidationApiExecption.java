package com.springboot.study.handler.ex;

import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationApiExecption extends RuntimeException{
	private static final long serialVersionUID = 1l; // 이거 뭔지 물어보기 ---------!!!!!!!!!!------------
	
	private Map<String, String> errorMap; // Map을 자료형으로 한 errorMap 변수 생성
	
	private CustomValidationApiExecption(String message) { // message를 매개변수로 받은 클래스 생성자
		super(message);
	}
	
	public CustomValidationApiExecption(String message, Map<String, String> errorMap) { // message와 errorMap을 매개변수로 받는 클래스 생성자
		super(message);
		this.errorMap = errorMap;
	}
}
