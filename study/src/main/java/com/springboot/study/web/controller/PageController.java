package com.springboot.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // 일반 @Controller 어노테이션으로 페이지를 띄워줄 수 있다.
public class PageController {
	
	
	@GetMapping("/index") // get요청으로 받는 맵핑 주소
	public String index() {
		return "index"; // index 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/board/list") // get요청으로 받는 맵핑 주소
	public String boardList() {
		return "board/board-list"; // 게시글 목록 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/board-info/{boardCode}") // get요청으로 받는 맵핑 주소
	// @PathVariable 어노테이션으로 위에 파라미터와 동일한 이름에 맵핑
	public String boardDtl(@PathVariable int boardCode) { 
		return "board/board-dtl"; // 게시글 내용 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/board")
	public String boardInsert() {
		return "board/board-insert";
	}
	
	@GetMapping("/board/{boardCode}")
	public String boardUpdate() {
		return "board/board-update";
	}
}
