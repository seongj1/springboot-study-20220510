package com.springboot.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 일반 @Controller 어노테이션으로 페이지를 띄워줄 수 있다.
public class PageController {
	
	
	@GetMapping("/index") // GetMapping으로 페이지 get 요청
	public String index() {
		return "index"; // index 페이지 맵핑주소
	}
	
	@GetMapping("/board")
	public String boardList() {
		return "board/board-list";
	}
}
