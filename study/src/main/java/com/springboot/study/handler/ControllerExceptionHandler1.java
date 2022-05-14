package com.springboot.study.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.handler.ex.CustomValidationApiException1;
import com.springboot.study.web.dto.CMRespDto1;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler1 {
		
	@ExceptionHandler
	public ResponseEntity<?> validationApiException(CustomValidationApiException1 e1){
		return new ResponseEntity<>(new CMRespDto1<Map<String, String>>(-1, e1.getMessage(), e1.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}
