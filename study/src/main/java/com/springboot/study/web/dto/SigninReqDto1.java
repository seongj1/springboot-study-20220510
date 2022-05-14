package com.springboot.study.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SigninReqDto1 {
	@NotBlank
	private String username;
	@NotBlank
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",
	message = "비밀번호를 확인해 주세요.")
	private String password;
}
