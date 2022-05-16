package com.springboot.study.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
	public List<Map<String, Object>> getBoardListAll() throws Exception;
	public List<Map<String, Object>> getBoardListByPage(int index) throws Exception;
	public int insertBoard(BoardMst boardMst) throws Exception; // 매개변수로 받은 boardMst를 int형식으로 바꿔서 반환해준다.
	public Map<String, Object> getBoardByBoardCode(int boardCode) throws Exception; // boardCode를 매개변수로 받아서 Map의 형태로 바꿔서 반환한다. /xml파일에서 db와 연결된 데이터를 선택해서 Map으로 가져온다.
	public int updateBoard(BoardMst boardMst) throws Exception;
	public int deleteBoard(int boardCode) throws Exception;
}
