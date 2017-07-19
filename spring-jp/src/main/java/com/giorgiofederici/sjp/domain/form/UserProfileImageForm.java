package com.giorgiofederici.sjp.domain.form;

import org.springframework.web.multipart.MultipartFile;

public class UserProfileImageForm {

	private MultipartFile file;
	private String username;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
