package com.springboot.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.study.filter.TestFilter1;
import com.springboot.study.filter.TestFilter2;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<TestFilter1> filter1(){ // 첫번째 필터를 싱행하는 메서드
		FilterRegistrationBean<TestFilter1> bean = new FilterRegistrationBean<TestFilter1>(new TestFilter1()); // 필터 객체를 생성해서 bean변수에 담는다.
		bean.addUrlPatterns("/*"); // 모든 요청에서 실행해라
		bean.setOrder(1); // 필터들 중에서 먼저 시작하는 순서 작은 것부터 시작된다.
		return bean; // IOC에서 bean 객체를 반환해준다.
	}
	
	@Bean
	public FilterRegistrationBean<TestFilter2> filter2(){
		FilterRegistrationBean<TestFilter2> bean = new FilterRegistrationBean<TestFilter2>(new TestFilter2()); // 필터 객체를 생성해서 bean변수에 담는다.
		bean.addUrlPatterns("/*"); // 모든 요청에서 실행해라
		bean.setOrder(0); // 필터들 중에서 먼저 시작하는 순서 작은 것부터 시작된다.
		return bean; // IOC에서 bean 객체를 반환해준다.
	}
}
