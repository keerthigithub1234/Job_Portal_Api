package com.example.jobportalapi.requestdto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class SocialProfileRequestDto {

	@NotNull(message="url cannot be null")
	@NotBlank(message="url cannot be blank")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	

}
