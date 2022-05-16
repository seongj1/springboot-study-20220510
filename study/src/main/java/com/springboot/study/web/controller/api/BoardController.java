package com.springboot.study.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.domain.board.BoardMst;
import com.springboot.study.service.board.BoardService;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public ResponseEntity<?> getBoardList(int page) throws Exception{
		List<BoardRespDto> boardRespDtos = boardService.getBoardListByPage(page);
		
		return new ResponseEntity<>(new CMRespDto<List<BoardRespDto>>(1, "게시글 목록 로드", boardRespDtos),HttpStatus.OK);
	}

	@PostMapping("/board") // Put요청으로 받는 맵핑주소
	public ResponseEntity<?> createBoard(@Valid BoardInsertReqDto boardInseertReqDto, BindingResult bindingResult) throws Exception{ //게시글을 생성하기 위한 메서드 / @Vliad 어노테이션으로 벨리데이션 체크 
		int boardCode = boardService.createBoard(boardInseertReqDto); // int자료형의 boardCode에 보더 서비스에서 메서드를 가져와 매개 변수로 boardInsertReqDto를 넣어준다.
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 작성 완료", boardCode), HttpStatus.OK); // CMRespDto에 Integer 자료형으로 입력값들과 boardCode를 받고 status로 OK를 보내준다.
	}
	
	@GetMapping("/board/{boardCode}") // get요청으로 받는 맵핑주소
	public ResponseEntity<?> getBoard(@PathVariable int boardCode) throws Exception{ // @PathVariable 어노테이션으로 위의 파라미터 값과 동일한 이름의 파라미터에 맵핑한다.
		BoardRespDto boardRespDto = boardService.getBoard(boardCode); // 보더 서비스에 getBoard메서드에 매개변수로 boardCode를 넣어주고 이것을 boardRespDto 변수에 넣는다.
		return new ResponseEntity<>(new CMRespDto<BoardRespDto>(1, "게시글 조회 성공", boardRespDto), HttpStatus.OK); // CMRespDto에 BoardRespDto 형태로 입력값들과 boardRespDto를 받고 status로 OK를 보내준다.
	}
	
	@PutMapping("/board/{boardCode}")
	public ResponseEntity<?> updateBoard(@PathVariable int boardCode, 
			@Valid BoardUpdateReqDto boardUpdateReqDto, BindingResult bindingResult) throws Exception{
		int resultBoardCode = boardService.updateBoard(boardCode, boardUpdateReqDto);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 수정 성공", resultBoardCode), HttpStatus.OK);
	}
	
	@DeleteMapping("board/{boardCode}")
	public ResponseEntity<?> deleteBoard(@PathVariable int boardCode) throws Exception{
		int resultBoardCode = boardService.deleteBoard(boardCode);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 삭제 성공", resultBoardCode), HttpStatus.OK);
	}
}
