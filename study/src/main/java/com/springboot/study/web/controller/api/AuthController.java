package com.springboot.study.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.SignupReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@GetMapping("/auth/signup/check/{username}") // get요청을 하는 어노테이션에 맵핑주소
	public ResponseEntity<?> checkUsername(@PathVariable String username){ // @PathVariable은 위에 어노테이션과 동일한 이름값을 갖는 파라미터에 맵핑되게 한다.
		CMRespDto<String> cmRespDto = null; // CMRespDto 변수를 null으로 초기화
		HttpStatus status = null; // Httpstatus 변수를 null로 초기화
		
		User user = new User(); // User객체 생성
		
		if(user.getUsername().equals(username)) { //user객체 안의 username와 변수로 받아온 username이 같다면
			cmRespDto = new CMRespDto<String>(-1, "사용할 수 없는 사용자이름입니다.", username); //cmRespDto 변수안에 자신을 생성하면서 code값에 -1, msg값에 "", data값에 username을 넣는다.
			status = HttpStatus.BAD_REQUEST; // status 변수안에 BAD_REQUEST 설정
		}else {
			cmRespDto = new CMRespDto<String>(1, "사용할 수 있는 사용자이름입니다.", username); //cmRespDto 변수안에 자신을 생성하면서 code값에 1, msg값에 "", data값에 username을 넣는다.
			status = HttpStatus.OK; // status 변수안에 OK 설정
		}
		return new ResponseEntity<>(cmRespDto, status); // if문 안에서 변수에 저장된 값을 ResponseEntity로 반환
	}
	
	@PostMapping("/auth/signup") // post요청을 하는 어노테이션에 맵핑주소
	public ResponseEntity<?> signup(@Valid SignupReqDto signupReqDto, BindingResult bindingResult){ // @Valid 어노테이션으로 validationCheck를 할 수 있다. / BindingResult로 Dto에 어노테이션 달려있는 것들을 담을 수 있다.
		
		
		return new ResponseEntity<>(new CMRespDto<SignupReqDto>(1, "회원가입 완료.", signupReqDto), HttpStatus.OK); // 오류가 없을 때 대한 결과 값과 status메세지를 리턴한다.
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		//List<String> roles = List.of("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"); //리스트에 바로 값 넣는 방법
		List<String> roles = List.of("ROLE_USER"); //리스트에 바로 값 넣는 방법
		user.setRoles(String.join(",", roles)); // 리스트에 들어있는 문자열들을 쉼표(,)로 구분해서 합쳐주는 방법
		
		userRepository.insertUser(user);
		
		return new ResponseEntity<>(new CMRespDto<User>(1, "회원가입완료", user), HttpStatus.OK);
	}
	
	
	@GetMapping("/authentication")
	public ResponseEntity<?> getAuthentication(@AuthenticationPrincipal PrincipalDetails principalDetails){ // 이 어노테이션이 세션으로 갈 수 잇게 해준다. 
		System.out.println(principalDetails.getUser().getUser_code()); 
		String password = principalDetails.getUser().getPassword();
		System.out.println(bCryptPasswordEncoder.matches("1234", password));
		return new ResponseEntity<>(new CMRespDto<PrincipalDetails>(1, "세션정보", principalDetails), HttpStatus.OK); // 세션정보와 status코드로 OK를 반환
	}
	
//	@GetMapping("/user")
//	public ResponseEntity<?> testUser(){
//		return new ResponseEntity<>(new CMRespDto<String>(1, "유저권한", "role_user"), HttpStatus.OK); // user 권한 입력값과 status코드로 Ok를 반환
//	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> testManager(){
		return new ResponseEntity<>(new CMRespDto<String>(1, "매니저권한", "role_manager"), HttpStatus.OK); // manager 권한 입력값과 status코드로 Ok를 반환
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> testAdmin(){
		return new ResponseEntity<>(new CMRespDto<String>(1, "관리자권한", "role_admin"), HttpStatus.OK); // admin 입력값과 status코드로 Ok를 반환
	}
}
