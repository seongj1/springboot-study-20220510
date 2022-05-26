package com.springboot.todolist.service;

import java.util.List;

import com.springboot.todolist.domain.ToDoListMst;
import com.springboot.todolist.web.dto.ToDoListModifiReqDto;

public interface ToDoListService {
	public List<ToDoListMst> getList();
	public boolean addToDo(ToDoListMst toDoListMst);
	public int modifiToDo(int id, ToDoListModifiReqDto toDoListModifiReqDto);
	public int removeToDo(int id);
}
