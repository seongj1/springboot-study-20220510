package com.springboot.todolist.web.controller.api;

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

import com.springboot.todolist.domain.ToDoListMst;
import com.springboot.todolist.service.ToDoListService;
import com.springboot.todolist.web.dto.ToDoListInsertReqDto;
import com.springboot.todolist.web.dto.ToDoListModifiReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ToDoListController {
	
	private final ToDoListService toDoListService;
	
	//리스트 전체 들고오기
	@GetMapping("/todo/list")
	public ResponseEntity<?> getListAll() {
		List<ToDoListMst> toDoList = toDoListService.getList();
		return new ResponseEntity<>(toDoList, HttpStatus.OK);
	}
	
	//내용 추가
	@PostMapping("/todo")
	public ResponseEntity<?> addToDo(@Valid @RequestBody ToDoListInsertReqDto toDoListInsertReqDto) {
		toDoListService.addToDo(toDoListInsertReqDto.toToDoList());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//내용 수정
	@PutMapping("/todo/{id}")
	public ResponseEntity<?> modifiToDo(@PathVariable int id, @Valid @RequestBody ToDoListModifiReqDto toDoListModifiReqDto) {
		int update =  toDoListService.modifiToDo(id, toDoListModifiReqDto);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}
	
	//내용 삭제
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> removeToDo(@PathVariable int id) {
		toDoListService.removeToDo(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
