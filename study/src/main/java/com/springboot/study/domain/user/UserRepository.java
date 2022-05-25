package com.springboot.study.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int insertUser(User user); // User생성을 위한 insert 메서드
	public User findUserByUsername(String username); // 데이터베이스에서 같은 username을 갖고 있는 테이블을 찾아 모든 데이터를 User로 반환해준다.
	public User findOAuth2UserByOAuth2Username(String oAuth2Username); // 데이터베이스에서 같은 oAuth2Username을 갖고 있는 테이블을 찾아 모든 데이터를 User로 반환해준다.
	public int updateProfileImg(User user); // 데이터베이스에서 user_dtl에 있는 profile_image_url 데이터를 선택해서 있다면 int형태로 반환해준다.
	public int updateProfile(User user);
}
