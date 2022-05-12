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

import com.springboot.study.web.controller.api.data.User;
import com.springboot.study.web.dto.AccountReqDto;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.SigninReqDto;
import com.springboot.study.web.dto.SignupReqDto;

@RestController 
public class UserController {
	
	@GetMapping("/user/{usercode}") //usercode를 파라미터 값으로 user를 get 요청하는 어노테이션
	public ResponseEntity<?> getUser(@PathVariable int usercode){ //위 어노테이션에 동일한 이름값을 갖는 파라미터에 매핑됨
		System.out.println(usercode); //usercode 확인
		return new ResponseEntity<>(10, HttpStatus.BAD_REQUEST); // 데이터 값은 10을 출력하지만 Http 응답 상태는 400error를 나타낸다. 
	}
	
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
	
	
	@PostMapping("/auth/signin") // post요청을 하는 어노테이션에 맵핑주소
	public ResponseEntity<?> signin(@Valid SigninReqDto signinReqDto, BindingResult bindingResult){// @Valid 어노테이션으로 validationCheck를 할 수 있다. / BindingResult로 Dto에 어노테이션 달려있는 것들을 담을 수 있다.
		
		User user = new User(); // User객체 생성
		if(signinReqDto.getUsername().equals(user.getUsername()) && signinReqDto.getPassword().equals(user.getPassword())){ // Dto에 있는 username과 password가 user안에 있는 username과 password가 같다면 
			return new ResponseEntity<>(new CMRespDto<User>(1, "로그인 성공", user), HttpStatus.OK); // CMRespDto 객체를 생성하면서 로그인 성공값들을 반환해주어라
		}else {
			return new ResponseEntity<>(new CMRespDto<User>(-1, "로그인 실패", user), HttpStatus.BAD_REQUEST); // CMRespDto 객체를 생성하면서 로그인 실패값들을 반환해주어라
		}
	}
	
	@PutMapping("/account/{username}") // put요청을 하는 어노테이션에 맵핑주소
	public ResponseEntity<?> updateUser(@PathVariable String username, // @PathVariable 어노테이션으로 위의 파라미터값과 동일한 이름의 파라미터 값에 매핑됨
			@Valid AccountReqDto accountReqDto, BindingResult bindingResult){ // @Valid 어노테이션으로 벨리데이션 체크를 할 수 있고 BindingResult로 변수들을 가지고 온다.
		
		User user = new User(); // user객체 생성
		if(!user.getUsername().equals(username)) { // user객체 안에 username과 입력값으로 받은 username이 같지 않다면
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원 조회 실패.", username), HttpStatus.BAD_REQUEST); // CMRespDto 객체를 생성하면서 회원조회 실패 값을 반환해주어라
		}
		
		user.setEmail(accountReqDto.getEmail()); //일치 한다면 user 이메일에 Dto로 받은 이메일로 바꾸어라
		user.setName(accountReqDto.getName()); //일치 한다면 user 이름에 Dto로 받은 이름으로 바꾸어라
		
		return new ResponseEntity<>(new CMRespDto<User>(1, "회원정보 수정완료", user), HttpStatus.OK); // CMRespDto 객체를 생성하면서 회원정보 수정이 완료된 user 데이터와 함께 값들을 반환해주어라
		
		
	}
	
	@DeleteMapping("/account/{username}") // delete요청을 하는 어노테이션의 맵핑주소
	public ResponseEntity<?> deleteUser(@PathVariable String username){ // @PathVariable 어노테이션으로 위의 파라미터값과 동일한 이름의 파라미터 값에 매핑됨
		User user = new User(); // user객체 생성
		if(!user.getUsername().equals(username)) { // user의 username과 입력값으로 받은 username이 일치하지 않다면
			return new ResponseEntity<>(new CMRespDto<String>(-1, "회원탈퇴 실패.", username), HttpStatus.BAD_REQUEST); // CMRespDto 객체를 생성하면서 회원탈퇴 실패 값을 반환해주어라
		}
		return new ResponseEntity<>(new CMRespDto<String>(1, "회원탈퇴 성공.", username), HttpStatus.OK); // CMRespDto 객체를 생성하면서 회원탈퇴 성공값을 반환해주어라
	}
	
	
	
	
	/*
	 * 1. 사용자이름 중복확인(/auth/signup/check/???)  
	 * -> User객체에 존재하는 사용자이름과 같으면 사용할 수 없는 사용자 이름입니다.
	 * -> 사용할 수 있는 사용자이름입니다.
	 * 
	 * 2. 회원가입(/auth/signup)
	 * -> 회원가입 정보 출력(console), 응답은 회원가입 완료.
	 * 
	 * 3. 로그인(/auth/signin)
	 * -> User객체의 정보와 일치하면 (username, password) 로그인 성공, 로그인 실패
	 * 
	 * 4. 회원수정(/account/aaa)
	 * -> name, email 수정 -> 회원수정 완료, 회원수정 실패
	 * 
	 * 5. 회원탈퇴(/account/aaa)
	 * -> 회원탈퇴 완료, 회원탈퇴 실패
	 * 
	 */
}
