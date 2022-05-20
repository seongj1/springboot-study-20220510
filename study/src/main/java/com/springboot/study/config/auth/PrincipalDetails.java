package com.springboot.study.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.study.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private User user; // user 객체 생성
	
	public PrincipalDetails(User user) { // 이 클래스를 생성할때 user도 같이 넣어준다.
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); // GrantedAuthority형태로 리스트를 만들어 변수에 담아둔다.
		
		user.getRoleList().forEach(r -> { // user객체에서 권한을 담은 메서드를 불러와서 리스트의 갯수만큼 반복한다.
			System.out.println("권한: " + r); // 반복할 때마다 어떤 권한을 가지고 있는지 콘솔창에 띄워준다.
			authorities.add(() -> r); // authorities변수에 받아온 권한들을 저장한다.
		});
		
		// 변수 안에 리스트 갯수만큼 반복할 때마다 리스트 안에 있는 권한들을 콘솔창에 찍어준다.
		authorities.forEach(r -> {System.out.println("리스트에 들어있는 권한" + r.getAuthority());}); 
			
		return authorities; // 리스트를 저장한 변수를 반환해준다.
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	
	// true false 값으로 판단해준다.
	
	@Override
	public boolean isAccountNonExpired() { //계정이 만료되었는지 확인
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { //비밀번호가 지정한 횟수 이상 틀리면 잠김
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { //자격 증명이 만료가 되면 계정사용 불가
		return true;
	}
	
	@Override
	public boolean isEnabled() { //휴면계정
		return true;
	}
}
