package com.springboot.study.web.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;
import com.springboot.study.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		//List<String> roles = List.of("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"); //리스트에 바로 값 넣는 방법
		List<String> roles = List.of("ROLE_USER"); //리스트에 바로 값 넣는 방법
		user.setRoles(String.join(",", roles)); // 리스트에 들어있는 문자열들을 쉼표(,)로 구분해서 합쳐주는 방법
		
		userRepository.insertUser(user);
		
		return new ResponseEntity<>(new CMRespDto<User>(1, "회원가입완료", user), HttpStatus.OK);
	}
	
	@PutMapping("/user/account/profile/img")
	public ResponseEntity<?> updateProfileImg(@RequestPart MultipartFile file){
		System.out.println(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/authentication")
	public ResponseEntity<?> getAuthentication(@AuthenticationPrincipal PrincipalDetails principalDetails){ // 이 어노테이션이 세션으로 갈 수 잇게 해준다. 
		System.out.println(principalDetails.getUser().getUser_code()); 
		String password = principalDetails.getUser().getPassword();
		System.out.println(bCryptPasswordEncoder.matches("1234", password));
		return new ResponseEntity<>(new CMRespDto<PrincipalDetails>(1, "세션정보", principalDetails), HttpStatus.OK); // 세션정보와 status코드로 OK를 반환
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> testUser(){
		return new ResponseEntity<>(new CMRespDto<String>(1, "유저권한", "role_user"), HttpStatus.OK); // user 권한 입력값과 status코드로 Ok를 반환
	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> testManager(){
		return new ResponseEntity<>(new CMRespDto<String>(1, "매니저권한", "role_manager"), HttpStatus.OK); // manager 권한 입력값과 status코드로 Ok를 반환
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> testAdmin(){
		return new ResponseEntity<>(new CMRespDto<String>(1, "관리자권한", "role_admin"), HttpStatus.OK); // admin 입력값과 status코드로 Ok를 반환
	}
}
