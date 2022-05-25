package com.springboot.study.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int insertUser(User user); // User생성을 위한 insert 메서드
	public User findUserByUsername(String username); // 데이터베이스에서 같은 username을 갖고 있는 데이터를 찾아 모든 데이터를 User로 반환해준다.
	public User findOAuth2UserByOAuth2Username(String oAuth2Username);
	public int updateProfileImg(User user);
}
