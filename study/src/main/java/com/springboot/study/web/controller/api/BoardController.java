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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api") // 이 클래스의 모든 맵핑 주소 앞에 api를 붙인다.
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list") // get요청으로 받는 맵핑주소
	public ResponseEntity<?> getBoardList(int page) throws Exception{ // 게시글 목록을 가져오기 위한 메서드
		List<BoardRespDto> boardRespDtos = boardService.getBoardListByPage(page); // Dto리스트 변수를 만들고 boardService에 메서드를 불러와서 page를 매개변수로 넣는다.
		// CMRespDto 객체를 생성하고 게시글 목록 로드 입력값과 boardRespDtos를 받고 status에 OK를 보내준다. 
		return new ResponseEntity<>(new CMRespDto<List<BoardRespDto>>(1, "게시글 목록 로드", boardRespDtos),HttpStatus.OK);
	}

	@PostMapping("/board") // Put요청으로 받는 맵핑주소
	public ResponseEntity<?> createBoard(@Valid @RequestBody BoardInsertReqDto boardInseertReqDto, BindingResult bindingResult) throws Exception{ //게시글을 생성하기 위한 메서드 / @Vliad 어노테이션으로 벨리데이션 체크 
		int boardCode = boardService.createBoard(boardInseertReqDto); // int자료형의 boardCode에 보더 서비스에서 메서드를 가져와 매개 변수로 boardInsertReqDto를 넣어준다.
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 작성 완료", boardCode), HttpStatus.OK); // CMRespDto에 Integer 자료형으로 입력값들과 boardCode를 받고 status로 OK를 보내준다.
	}
	
	@GetMapping("/board/{boardCode}") // get요청으로 받는 맵핑주소
	public ResponseEntity<?> getBoard(@PathVariable int boardCode) throws Exception{ // @PathVariable 어노테이션으로 위의 파라미터 값과 동일한 이름의 파라미터에 맵핑한다.
		BoardRespDto boardRespDto = boardService.getBoard(boardCode); // 보더 서비스에 getBoard메서드에 매개변수로 boardCode를 넣어주고 이것을 boardRespDto 변수에 넣는다.
		return new ResponseEntity<>(new CMRespDto<BoardRespDto>(1, "게시글 조회 성공", boardRespDto), HttpStatus.OK); // CMRespDto에 BoardRespDto 형태로 입력값들과 boardRespDto를 받고 status로 OK를 보내준다.
	}
	
	@PutMapping("/board/{boardCode}") // put요청으로 받는 맵핑주소
	public ResponseEntity<?> updateBoard(@PathVariable int boardCode, // @PathVariable 어노테이션으로 위의 파라미터 값과 동일한 이름의 파라미터에 맵핑한다. 
			@Valid @RequestBody BoardUpdateReqDto boardUpdateReqDto, BindingResult bindingResult) throws Exception{ // @Valid 어노테이션으로 Dto안에 변수를 벨리데이션 체크 가능
		int resultBoardCode = boardService.updateBoard(boardCode, boardUpdateReqDto); // int 자료형의 변수에 boardService에 메서드를 가져와 매개변수에 boardCode와 boardUpdateReqDto를 넣어준다.
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 수정 성공", resultBoardCode), HttpStatus.OK); // CMRespDto에 게시글 수정 성공 입력값과 resultBoardCode를 받고 status로 OK를 보내준다.
	}
	
	@DeleteMapping("board/{boardCode}") // delete요청으로 받는 맵핑주소
	public ResponseEntity<?> deleteBoard(@PathVariable int boardCode) throws Exception{ // @PathVariable 어노테이션으로 위의 파라미터 값과 동일한 이름의 파라미터에 맵핑한다.
		int resultBoardCode = boardService.deleteBoard(boardCode); // int자료형 변수에 boardService에 메서드를 불러와서 매개변수로 boardCode를 넣는다.
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 삭제 성공", resultBoardCode), HttpStatus.OK); // CMRespDto에 게시글 삭제 성공 입력값과 resultBoardCode를 받고 status로 OK를 보내준다.
	}
}
