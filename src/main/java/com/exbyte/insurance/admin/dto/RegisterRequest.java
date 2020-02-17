package com.exbyte.insurance.admin.dto;

import javax.validation.Valid;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.EmailAuthKey;
import com.exbyte.insurance.admin.domain.Password;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RegisterRequest {
	@Valid
	private String adminId;
	
	@Valid
	private Password adminPw;
	
	@Valid
	private String adminName;
	
	@Valid
	private String adminEmail;
	
	@Valid
	private String adminPhone;
	
	private String adminCallNumber;
	
	@Valid
	private String adminPosition;
	
	@Valid
	private int adminPoint;
	
	@Builder
	public RegisterRequest(@Valid String adminId, @Valid String adminPw, @Valid String adminName, @Valid String adminEmail,
			@Valid String adminPhone, String adminCallNumber, @Valid String adminPosition, @Valid int adminPoint){
		this.adminId = adminId;
		this.adminPw = Password.builder().adminPw(adminPw).build();
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPhone = adminPhone;
		this.adminCallNumber = adminCallNumber;
		this.adminPosition = adminPosition;
		this.adminPoint = adminPoint;
	}
	
	public AdminVO toEntity(EmailAuthKey EmailAuthKey) {
		return AdminVO.builder()
				.adminId(adminId)
				.adminPw(adminPw.generateHashPassword(adminPw.getAdminPw()))
				.adminName(adminName)
				.adminEmail(adminEmail)
				.adminPhone(adminPhone)
				.adminCallNumber(adminCallNumber)
				.adminPosition(adminPosition)
				.adminPoint(adminPoint)
				.adminAuthKey(EmailAuthKey.getAuthKey())
				.sessionKey("none")
				.build();
	}
	
	
	
	
	
	
}
