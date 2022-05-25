package com.springboot.study.service.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	
	@Value("${file.path}") // 생성되는 시점에 yml에 있는 경로 값을 밑에 변수에 set해준다.
	private String filePath;
	
	private final UserRepository userRepository;
	
	@Override
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails) {
		if(file != null) {
			String originalFileName = file.getOriginalFilename();
			String tempFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
			Path uploadPath = Paths.get(filePath, "profile/" + tempFileName); //
			
			File f = new File(filePath + "profile");
			if(!f.exists()) {
				f.mkdirs();
			}
			
			try {
				Files.write(uploadPath, file.getBytes());
				
				User user = principalDetails.getUser();
				user.setProfile_img_url(tempFileName);
				
				return userRepository.updateProfileImg(user) > 0 ? true: false;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
