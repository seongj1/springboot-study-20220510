package com.springboot.study.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int user_code;
	private String email;
	private String name;
	private String username;
	private String password;
	private String roles; // ROLE_USER,ROLE_MANAGER,ROLE_ADMIN
	
	public List<String> getRoleList(){ // 각 user의 권한들을 반환해줄 메서드
		if(this.roles.length() > 0) { // roles의 갯수가 0보다 높다면 
			return Arrays.asList(this.roles.split(",")); // roles안에 있는 것들을 (,)를 기준으로 배열로 만들고 리스트로 만들어서 반환해준다.
		}
		return new ArrayList<String>(); // 아니라면 String형태의 새로운 리스트를 만들어 반환해준다.
	}
}
