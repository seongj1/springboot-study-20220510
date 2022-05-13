package com.springboot.study.web.dto.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.study.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardInsertReqDto { // 게시물을 만들 때 필요한 변수들을 만든 클래스 
	@NotBlank // 빈칸까지 체크해주는 어노테이션
	private String title;
	@NotBlank
	private String content;
	@NotNull // 빈값을 체크해주는 어노테이션
	private int usercode;
	
	public BoardMst toBoardMstEntity() { // BoardMst에 전달해줄 변수와 이름을 설정한 toEntity
		return BoardMst.builder() // 리턴값으로 빌드된 이름과 변수들을 보내준다.
				.board_title(title)
				.board_content(content)
				.board_writer(usercode)
				.build();
	}
}
