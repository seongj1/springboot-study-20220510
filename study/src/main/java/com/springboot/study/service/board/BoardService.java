package com.springboot.study.service.board;

import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;

public interface BoardService {
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception; // 반환값이 int 자료형의 메서드 BoardInsertReqDto를 매개변수로 받는다.
	public BoardRespDto getBoard(int boardCode) throws Exception; // 반환값이 BoardRespDto 형태의 메서드 매개변수로 boardCode를 받는다.
}
