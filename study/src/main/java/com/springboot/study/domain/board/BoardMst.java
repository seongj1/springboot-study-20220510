package com.springboot.study.domain.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardMst { // DB에 있는 컬럼명과 같게 변수이름을 설정해준다.
	private int board_code;
	private String board_title;
	private String board_content;
	private int board_writer;
	private int board_count;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	
}
