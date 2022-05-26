package com.springboot.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todolist.domain.ToDoListMst;
import com.springboot.todolist.domain.ToDoListRepository;
import com.springboot.todolist.web.dto.ToDoListModifiReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService{
	
	private final ToDoListRepository toDoListRepository;
	
	
@Override
	public List<ToDoListMst> getList() {
	List<ToDoListMst> toDoListMsts = toDoListRepository.getList();
		return toDoListMsts;
	}
	
	@Override
	public boolean addToDo(ToDoListMst toDoListMst) {
		return toDoListRepository.addToDo(toDoListMst) > 0;
	}
	
	@Override
	public int modifiToDo(int id, ToDoListModifiReqDto toDoListModifiReqDto) {
		ToDoListMst toDoListMst = toDoListModifiReqDto.toToDoListMstEntity(id);
		return toDoListRepository.modifiToDo(toDoListMst) > 0 ? id : 0;
	}
	
	@Override
	public int removeToDo(int id) {
		return toDoListRepository.removeToDo(id) > 0 ? id : 0;
	}
}
