package com.springboot.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller // 일반 @Controller 어노테이션으로 페이지를 띄워줄 수 있다.
@RequiredArgsConstructor
public class PageController {
	
	
	@GetMapping("/index") // get요청으로 받는 맵핑 주소
	public String index() {
		return "index"; // index 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/auth/signin") // get요청으로 받는 맵핑 주소 
	public String signin() { 
		return "auth/signin"; // signin 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/user/account/mypage")
	public String mypage() {
		return "account/mypage";
	}
	
	@GetMapping("/board/list") // get요청으로 받는 맵핑 주소
	public String boardList() { // 어노테이션이 세션 역할 principal 변수에 담아라
		return "board/board-list"; // 게시글 목록 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/board-info/{boardCode}") // get요청으로 받는 맵핑 주소
	public String boardDtl(@PathVariable int boardCode) { // @PathVariable 어노테이션으로 위에 파라미터와 동일한 이름에 맵핑
		return "board/board-dtl"; // 게시글 내용 페이지 jsp 파일 이름 
	}
	
	@GetMapping("/board")
	public String boardInsert() { // get요청으로 받는 맵핑 주소
		return "board/board-insert"; // 게시글 작성 페이지 jsp 파일 이름
	}
	
	@GetMapping("/board/{boardCode}") // get요청으로 받는 맵핑 주소
	public String boardUpdate() {
		return "board/board-update"; // 게시글 수정 페이지 jsp 파일 이름
	}
}
