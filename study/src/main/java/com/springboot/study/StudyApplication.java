package com.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class StudyApplication {

	public static void main(String[] args) { //서버 실행하는 Main메서드
		SpringApplication.run(StudyApplication.class, args);
	}

}
