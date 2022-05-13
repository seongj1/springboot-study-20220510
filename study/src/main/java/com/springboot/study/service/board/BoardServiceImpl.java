package com.springboot.study.service.board;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.board.BoardMst;
import com.springboot.study.domain.board.BoardRepository;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 이 어노테이션이 있음으로 밑에 @Autowild를 해줄 필요가 없다.
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository; // 보드 레퍼지토리를 객체를 상수로 받는다.
	
	@Override
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception{ // 게시글을 작성하기 위한 메서드 / boardInsertReqDto를 매개변수로 받는다.
		
		BoardMst boardMst = boardInsertReqDto.toBoardMstEntity(); // boardMst안에 insertDto에 만들어 두었던 toEntity를 넣는다.
		
		int result = boardRepository.insertBoard(boardMst); // 레퍼지토리에 메서드에 매개변수로 toEntity를 넣고 int result 변수에 담는다.
		
		if(result > 0) { // result가 0보다 크다면
			return boardMst.getBoard_code(); // boardMst안에 Board_code를 반환해라
		}
		return 0; // 아니라면 0을 반환해라
	}
	
	@Override
	public BoardRespDto getBoard(int boardCode) throws Exception { // 게시글을 조회하기 위한 메서드 / boardCode를 매개변수로 받는다.
		 Map<String, Object> boardMap = boardRepository.getBoardByBoardCode(boardCode); // Map의 형태로 boardMap이라는 변수를 만들어 레퍼지토리에 getBoardByBoardCode메서드를 받아서 매개변수로  baordCode를 넣어준다.
		return BoardRespDto.builder() // boardMap안에 데이터를 가져와서 다운캐스팅하여 Dto에 빌더로 넣어준다.
				.boardCode((Integer) (boardMap.get("board_code")))
				.title((String)(boardMap.get("board_title")))
				.content((String) (boardMap.get("board_content")))
				.usercode((Integer) (boardMap.get("board_writer")))
				.username((String) (boardMap.get("board_username")))
				.build();
	}
}
