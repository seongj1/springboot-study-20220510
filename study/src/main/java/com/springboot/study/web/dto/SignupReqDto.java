package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupReqDto {
	@NotBlank(message = "빈 값일 수 없습니다.") // null,빈 문자열, 스페이스만 있는 문자열을 걸러내기 위한 어노테이션
	@Email(message = "이메일 형식을 확인해 주세요.") //이메일 형식 체크 어노테이션
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String username;
	@NotBlank
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",
	message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상은 포함되어야 하며 8 ~ 20자의 비밀번호여야 합니다.")
	private String password;
}
