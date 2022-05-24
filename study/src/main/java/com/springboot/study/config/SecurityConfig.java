package com.springboot.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.study.config.oauth2.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity  
// 기존의 WebSecurityConfigurerAdapter의 설정을 비활성화 시키고 현재 클래스(SecurityConfig)의 설정을 따르겠다.
@Configuration // 
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests() // 인증요청
			.antMatchers("/api/board/**", "/", "/board/list") // URI 지정
			.authenticated() // 인증을 거쳐라.
			.antMatchers("/api/v1/user/**", "/user/account/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.antMatchers("/api/v1/manager/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.antMatchers("/api/v1/admin/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.anyRequest() // 다른 모든 요청은
			.permitAll() // 모두에게 권한을 주겠다.(권한이 필요업다)
			.and()
			.formLogin() // 파라미터를 받아서 로그인을 하겠다.
			.loginPage("/auth/signin") // 로그인 페이지 get요청 (view)
			.loginProcessingUrl("/auth/signin") // 로그인 post요청(PrincipalDetailsService -> loadUserByUesrname() 호출)
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login()
			.loginPage("/auth/signin")
			.userInfoEndpoint()
			/*
			 * 1. 코드를 받는다(google, naver, kakao 등 로그인 요청을 했을 때 부여되는 코드번호)
			 * 2. 에세스토큰을 발급받는다.(JWT)
			 * 3. 스코프 정보에 접근할 수 있는 권한이 생긴다.
			 * 4. 해당 정보를 시큐리티에서 활용하면 됨
			 */
			.userService(principalOauth2UserService) 
			.and()
			.defaultSuccessUrl("/");
	}
	
	
}
