package com.springboot.todolist.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToDoListRepository {
	public int addToDo(ToDoListMst toDoListMst);
	public List<ToDoListMst> getList();
	public int modifiToDo(ToDoListMst toDoListMst);
	public int removeToDo(int id);
}
