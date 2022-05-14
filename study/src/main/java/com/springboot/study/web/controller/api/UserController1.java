package com.springboot.study.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.web.controller.api.data.User1;
import com.springboot.study.web.dto.AccountReqDto1;
import com.springboot.study.web.dto.CMRespDto1;
import com.springboot.study.web.dto.SigninReqDto1;
import com.springboot.study.web.dto.SignupReqDto1;

@RestController
public class UserController1 {
	
	@GetMapping("/user/{usercode}")
	public ResponseEntity<?> getUser(@PathVariable String usercode){
		return new ResponseEntity<>(10, HttpStatus.OK);
	}
	
	@GetMapping("/auth/signup/check/{username}")
	public ResponseEntity<?> checkUsername(@PathVariable String username){
		CMRespDto1<String> cmRespDto1 = null;
		HttpStatus status = null;
		
		User1 user1 = new User1();
		
		if(user1.getUsername().equals(username)) {
			cmRespDto1 = new CMRespDto1<String>(-1, "사용할 수 없는 사용자 이름입니다.", username);
			status = HttpStatus.BAD_REQUEST;
		}else {
			cmRespDto1 = new CMRespDto1<String>(1, "사용할 수 있는 사용자 이름입니다.", username);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(cmRespDto1, status);
	}
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@Valid SignupReqDto1 signupReqDto1, BindingResult bindingResult){
		return new ResponseEntity<>(new CMRespDto1<SignupReqDto1>(1, "회원가입 완료", signupReqDto1), HttpStatus.OK);
	}
	
	@PostMapping("/auth/signin")
	public ResponseEntity<?> signin(@Valid SigninReqDto1 signinReqDto1, BindingResult bindingResult){
		User1 user1 = new User1();
		if(signinReqDto1.getUsername().equals(user1.getUsercode()) && signinReqDto1.getPassword().equals(user1.getPassword())) {
			return new ResponseEntity<>(new CMRespDto1<User1>(1, "로그인 성공", user1), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto1<User1>(-1, "로그인 실패", user1), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/account/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, 
			@Valid AccountReqDto1 accountReqDto1, BindingResult bindingResult){
		
		User1 user1 = new User1();
		if(!user1.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto1<String>(-1, "회원조회 실패.", username), HttpStatus.BAD_REQUEST);
		}
		
		user1.setEmail(accountReqDto1.getEmail());
		user1.setName(accountReqDto1.getName());
		
		return new ResponseEntity<>(new CMRespDto1<User1>(1, "회원정보 수정완료", user1), HttpStatus.OK);
	}
	
	@DeleteMapping("/account/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username){
		User1 user1 = new User1();
		if(!user1.getUsername().equals(username)) {
			return new ResponseEntity<>(new CMRespDto1<String>(-1, "회원탈퇴 실패.", username), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new CMRespDto1<String>(1, "회원탈퇴 성공.", username), HttpStatus.OK);
	}
}
