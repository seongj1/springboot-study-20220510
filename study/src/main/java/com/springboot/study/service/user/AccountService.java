package com.springboot.study.service.user;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;

public interface AccountService {
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails); // 프로필 수정을 위한 인터페이스 메서드
}
