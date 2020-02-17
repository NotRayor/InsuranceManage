package com.exbyte.insurance.admin.exception;

import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{
	
	private String email;
	
	public EmailDuplicateException() {
		super("이메일 중복 예외 발생");
	}
	
	public EmailDuplicateException(String email) {
		super(email + "중복 예외 발생 ");
		this.email = email;
	}
	
	
}
