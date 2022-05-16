package com.springboot.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 일반 @Controller 어노테이션으로 페이지를 띄워줄 수 있다.
public class PageController {
	
	
	@GetMapping("/index") // get요청으로 받는 맵핑 주소
	public String index() {
		return "index"; // index 페이지 맵핑주소
	}
	
	@GetMapping("/board") // get요청으로 받는 맵핑 주소
	public String boardList() {
		return "board/board-list"; // 게시글 목록 페이지 맵핑 주소
	}
}
