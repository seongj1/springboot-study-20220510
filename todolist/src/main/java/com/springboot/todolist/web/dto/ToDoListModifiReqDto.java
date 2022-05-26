package com.springboot.todolist.web.dto;

import javax.validation.constraints.NotBlank;

import com.springboot.todolist.domain.ToDoListMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoListModifiReqDto {
	
	@NotBlank
	private String content;
	
	public ToDoListMst toToDoListMstEntity(int id) {
		return ToDoListMst.builder()
				.id(id)
				.content(content)
				.build();
	}
	
}
