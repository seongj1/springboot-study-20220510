package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class); // 이 페이지에서 사용하기 위한 LOGGER생성
	
	@Pointcut("within(com.springboot.study.test..*)")
	private void pointcut() {}
	
	@Around("pointcut()") // @Around 어노테이션으로 경로안에 해당하는 것들을 가져온다.
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{ // 
		//long startAt = System.currentTimeMillis(); // 메서드가 시작하는 시간
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Map<String, Object> params = getPrams(pjp);
		
		LOGGER.info("--Advice Call: {}({}) = {}", pjp.getSignature().getDeclaringTypeName(), 
				pjp.getSignature().getName(), params); // 메서드를 요청할 때 LOGGER에 보여줄 값과 데이터를 넣는다.
		
		Object result = pjp.proceed(); // filter의 체인과 같은 역할 / 해당메서드가 실행되는 기점
		
		//long endAt = System.currentTimeMillis(); // 메서드가 끝나는 시간
		stopWatch.stop();
		LOGGER.info("--Advice End: {}({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(), 
				pjp.getSignature().getName(), result, stopWatch.getTotalTimeSeconds());  // 메서드가 끝날 때 LOGGER에 보여줄 값과 데이터를 넣는다.
		
		return result; // 
	}
	
	private Map<String, Object> getPrams(ProceedingJoinPoint pjp){
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		Object[] args = pjp.getArgs();
		String[] argNames = ((CodeSignature)pjp.getSignature()).getParameterNames();
		
		for(int i = 0; i < args.length; i++) {
			params.put(argNames[i], args[i]);
		}
		return params;
	}
}
