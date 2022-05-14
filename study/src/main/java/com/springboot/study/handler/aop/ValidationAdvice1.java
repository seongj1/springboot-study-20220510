package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.springboot.study.handler.ex.CustomValidationApiException1;

@Aspect
@Component
public class ValidationAdvice1 {
	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);
	
	@Around("execution(* com.springboot.study.web.controller.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			BindingResult bindingResult = (BindingResult)arg;
			if(bindingResult.hasErrors()) {
				Map<String, String> errorMap = new HashMap<>();
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				LOGGER.error("Validation AOP 실행됨");
				
				throw new CustomValidationApiException1("유효성 검사 실패", errorMap);
			}
		}
		
		return proceedingJoinPoint.proceed();
	}
}
