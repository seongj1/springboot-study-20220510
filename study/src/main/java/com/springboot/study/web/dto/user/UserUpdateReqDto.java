package com.springboot.study.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateReqDto {
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String name;
	
}
