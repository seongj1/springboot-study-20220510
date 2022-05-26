package com.springboot.todolist.web.dto;

import javax.validation.constraints.NotBlank;

import com.springboot.todolist.domain.ToDoListMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoListInsertReqDto {
	
	@NotBlank
	private String content;
	
	public ToDoListMst toToDoList() {
		return ToDoListMst.builder()
				.content(content)
				.build();
	}
}
