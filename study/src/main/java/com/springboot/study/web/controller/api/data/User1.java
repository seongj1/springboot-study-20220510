package com.springboot.study.web.controller.api.data;

import lombok.Data;

@Data
public class User1 {
	private int usercode;
	private String username;
	private String password;
	private String email;
	private String name;
	
	public User1() {
		usercode = 20220001;
		username = "aaa";
		password = "a12345678!";
		email = "dich24@naver.com";
		name = "윤성준";
	}
}
