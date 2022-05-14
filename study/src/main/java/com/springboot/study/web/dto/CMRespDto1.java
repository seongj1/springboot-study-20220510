package com.springboot.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDto1<T > {
	private int code;
	
	private String msg;
	
	private T data;
}
