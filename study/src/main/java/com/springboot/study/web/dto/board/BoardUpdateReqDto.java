package com.springboot.study.web.dto.board;

import javax.validation.constraints.NotBlank;

import com.springboot.study.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateReqDto { // 게시글을 수정할 때 사용할 Dto
	
	@NotBlank
	private String title; // 게시글 제목 변수
	@NotBlank
	private String content; // 게시글 내용 변수
	
	public BoardMst toBoardMstEntity(int boardCode) { // Entity로 받아서 넘겨줄 메서드
		return BoardMst.builder()
				.board_code(boardCode)
				.board_title(title)
				.board_content(content)
				.build();
	}
	
}
