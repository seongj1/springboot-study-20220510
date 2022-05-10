package com.springboot.study.web.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  //@ResponseBody와 @Controller가 합쳐진 어노테이션 따로 @ResponseBody를 해줄 필요가 없어진다.(이 클래스 안에 모든 메서드는 문자를 반환한다.)
public class FirstController {
	@GetMapping("/hello") // Get요청으로 받는 맵핑주소 (@RequestMapping을 줄여서 표현한 방법)
	public String hello() { // String 자료형의 hello메서드
		return "hello"; // 문자열 hello를 반환해준다.
	}
	
	
	
	@GetMapping("/add")
	public String add(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			result += i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/sub")
	public String sub(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			result -= i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/mul")
	public String mul(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			result *= i;
		}
		return Integer.toString(result);
	}
	@GetMapping("/div")
	public String div(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i : values) {
			if(result == 0) {
				return "0으로 계산 할 수 없습니다.";
			}else {
				result /= i;
			}
		}
		
		return Integer.toString(result);
	}

	/*
	 * 요청 리소스 add(덧셈), sub(뺄셈), mul (곱셈), div(나눗셈)
	 * 파라미터 = a, b
	 * 
	 * div 파라미터가 둘중 하나라도 0이면 '0으로 계산할 수 없습니다.'
	 * 
	 */
	
	

}
