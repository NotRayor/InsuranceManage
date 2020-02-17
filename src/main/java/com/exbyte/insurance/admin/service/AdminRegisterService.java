package com.exbyte.insurance.admin.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.EmailAuthKey;
import com.exbyte.insurance.admin.dto.RegisterRequest;
import com.exbyte.insurance.admin.persistence.AdminDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminRegisterService {
	
	private AdminDAO adminDAO;
	
	public AdminRegisterService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	public String hashAdminPw(String adminPw) throws Exception {
		return BCrypt.hashpw(adminPw, BCrypt.gensalt());
	}
	
	public AdminVO registerAccount(RegisterRequest registerRequest) throws Exception {
		AdminVO admin = registerRequest.toEntity(EmailAuthKey.generateCode(20));
		adminDAO.create(admin);
		
		return admin;
	}
	
	
	
}
