package com.springboot.todolist.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.springboot.todolist.handler.exception.CustomValidationApiException;

@Aspect
@Component
public class ValidationAop {
	
	@Pointcut("within(com.springboot.todolist.web.controller..*)")
	private void pointcut() {}
	
	@Pointcut("@annotation(com.springboot.todolist.annotation.aop.Validation)")
	private void enableValid() {}
	
	@Before("pointcut() && enableValid()")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<String, String>();
					bindingResult.getFieldErrors().forEach(r -> {
						errorMap.put(r.getField(), r.getDefaultMessage());
					});
					throw new CustomValidationApiException("유효성 검사 실패", errorMap);
				}
					
			}
		}
	}
}
