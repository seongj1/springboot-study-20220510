package com.springboot.study.config.auth;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findUserByUsername(username); // 같은 username으로 찾은 데이터를 User형태의 변수에 담아준다.
		System.out.println(userEntity); // user 데이터들을 콘솔에 찍어본다.
		System.out.println("로그인 요청?"); // 메서드가 실행이 되었는지 확인
		
		
				
		
		return new PrincipalDetails(userEntity, new HashMap<String, Object>()); // user의 데이터를 PincipalDetails 객체를 생성하면서 반환해준다.
	}
}
