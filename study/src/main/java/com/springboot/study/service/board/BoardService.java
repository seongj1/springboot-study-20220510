package com.springboot.study.service.board;

import java.util.List;

import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

public interface BoardService {
	public List<BoardRespDto> getBoardListAll() throws Exception; // 게시글 전체 목록을 가져오기 위한 메서드 / 반환값이 Dto에 대한 List형태의 메서드 
	public List<BoardRespDto> getBoardListByPage(int page) throws Exception; // 선택한 게시글 목록을 가져오기 위한 메서드 / 반환값이 Dto에 대한 List형태의 메서드 int 자료형의 page를 매개변수로 받는다.
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception; // 반환값이 int 자료형의 메서드 BoardInsertReqDto를 매개변수로 받는다.
	public BoardRespDto getBoard(int boardCode) throws Exception; // 반환값이 BoardRespDto 형태의 메서드 매개변수로 boardCode를 받는다.
	public int updateBoard(int boardCode, BoardUpdateReqDto boardUpdateReqDto) throws Exception; // 반환값이 int 자료형의 메서드 boardCode와 Dto를 매개변수로 받는다.
	public int deleteBoard(int boardCode) throws Exception; // 반환값이 int 자료형의 메서드 boardCode를 매개변수로 받는다.
}
