package com.springboot.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration // componant 같은 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/api/board/**", "/", "/board/list") // 이 요청이 들어오면
			.authenticated() // 인증을 거쳐라.
			.antMatchers("/api/v1/user/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.antMatchers("/api/v1/manager/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.antMatchers("/api/v1/admin/**") // 이 요청이 들어오면
			.access("hasRole('ROLE_ADMIN')") // 이 권한을 가진 사람만 응답해줘라
			.anyRequest() // 다른 모든 요청은
			.permitAll() // 권한이 필요없다.
			.and()
			.formLogin() // form 데이터로 로그인을 시도한다.
			.loginPage("/auth/signin") // 로그인 페이지 get요청 (view)
			.loginProcessingUrl("/auth/signin") // 로그인 post요청(PrincipalDetailsService -> loadUserByUesrname() 호출)
			.defaultSuccessUrl("/");
	}
	
	
}
