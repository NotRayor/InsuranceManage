package com.exbyte.insurance.admin.domain;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Password {

	String adminPw;
	
	public String generateHashPassword(String adminPw) {
		return BCrypt.hashpw(adminPw, BCrypt.gensalt());
	}
	
	@Builder
	public Password(String adminPw){
		this.adminPw = adminPw;
	}
	
}
