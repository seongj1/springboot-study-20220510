package com.springboot.study.web.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardRespDto { // 게시글 목록을 반환해주기 위한 Dto 
	private int boardCode;
	private String title;
	private String content;
	private int usercode;
	private String username;
	private int board_count;
	
}
