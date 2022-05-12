package com.springboot.study.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.handler.ex.CustomValidationApiExecption;
import com.springboot.study.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationApiExecption.class) // 예외가 발생했을 때 낚아채는 어노테이션
	public ResponseEntity<?> validationApiException(CustomValidationApiExecption e){ // 잡아온 클래스를 자료형으로 하는 변수를 매개변수로 받는다.
		// CMRespDto에 받아온 예외 사항들에 대한 출력값을 설정한 다음에 status값과 같이 반환한다.
		return new ResponseEntity<>(new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST); 
	}
}
