package com.springboot.study.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class); // 이 페이지에서 사용하기 위한 LOGGER생성
	
	@Around("within(com.springboot.study..*)") // @Around 어노테이션으로 경로안에 해당하는 것들을 가져온다.
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{ // 
		long startAt = System.currentTimeMillis(); // 메서드가 시작하는 시간
		
		LOGGER.info("--------Advice Call: {}({}) = {}", pjp.getSignature().getDeclaringTypeName(), 
				pjp.getSignature().getName(), "데이터"); // 메서드를 요청할 때 LOGGER에 보여줄 값과 데이터를 넣는다.
		
		Object result = pjp.proceed(); // filter의 체인과 같은 역할
		
		long endAt = System.currentTimeMillis(); // 메서드가 끝나는 시간
		
		LOGGER.info("--------Advice End: {}({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(), 
				pjp.getSignature().getName(), "데이터", endAt - startAt);  // 메서드가 끝날 때 LOGGER에 보여줄 값과 데이터를 넣는다.
		
		return result; // 
	}
}
