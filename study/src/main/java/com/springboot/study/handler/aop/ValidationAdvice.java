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

import com.springboot.study.handler.ex.CustomValidationApiExecption;

/*
 * Aspect: 여러 핵심 기능에 적용될 관심사 모듈을 의미한다. Aspect에는 구체적인 기능을 구현한 Advice와 Advice가 
 * 			어디(Target)에서 적용될지 결정하는 PointCut을 포함하고 있다.
 * 
 * Target: 공통 기능을 부여할 대상. 즉, 핵심 기능을 담당하는 비즈니스 로직이고, 어떤 관심사들과도 관계를 맺지 않는다.
 * 
 * Advice: 공통 기능을 담은 구현체. Advice는 Aspect가 무엇을 언제 적용할 지를 정의하는 것.
 * 
 * PointCut 공통 기능이 적용될 대상을 결정하는 것.
 * 
 * JoinPoint: Advice가 적용될 지점을 의미한다. Spring에서는 메서드에만 제공이 된다.
 * 
 */

@Aspect
@Component
public class ValidationAdvice {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class); // LoggerFactory.getLogger에 매개변수는 이 클래스 이름으로 넣는다. / 파이널 상수이기 때문에 변수명은 대문자로만 이루어져있다.
	
	@Around("execution(* com.springboot.study.web.controller.api.*Controller.*(..))") // @Aspect 어노테이션이 @Around 어노테이션 안에 해당되는 것들을 가지고 온다.
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{ // proceedingJoinPoint가 @Around 경로안에 있는 매개변수를 가지고 온다.
		Object[] args = proceedingJoinPoint.getArgs(); // Object로 업캐스팅해서 가져온 매개변수들을 저장한다.
		for(Object arg : args) { // args의 갯수만큼 반복한다.
			if(arg instanceof BindingResult) { // arg안에 값이 BindingResult라면 아래 실행문을 이행해라
				BindingResult bindingResult = (BindingResult) arg; // arg를 다운캐스팅해서 bindingResult 변수안에 넣어라
				if(bindingResult.hasErrors()) { // bindingResult안에 에러가 있다면
					Map<String, String> errorMap = new HashMap<String, String>(); //Map 객체를 생성한다.
					for(FieldError error : bindingResult.getFieldErrors()) { // bindingResult안에 error의 갯수 만큼 반복해라
						errorMap.put(error.getField(), error.getDefaultMessage()); // errorMap에다가 에러 필드의 이름과 에러 메세지를 넣어라
					}
					LOGGER.error("Validation AOP 실행됨"); // 
					
					throw new CustomValidationApiExecption("유효성 검사 실패", errorMap); // CustomValidationApiExecption객체를 만들면서 매개변수로 메새지와 errorMap을 함께 날린다.
					
				}
			}
		}
		
		return proceedingJoinPoint.proceed(); // filter의 체인과 같은 역할을 한다.
	}
}
