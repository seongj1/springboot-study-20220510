package com.springboot.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Value("${file.path}")
	private String filePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/image/**")
		.addResourceLocations("file:///" + filePath) // 위에 있는 경로를 이것으로 대체해라
		.setCachePeriod(60*60) // 캐시 지속시간 설정(초)
		.resourceChain(true) // 필터의 체인과 같은 역할
		.addResolver(new PathResourceResolver()); // 
	}
}
