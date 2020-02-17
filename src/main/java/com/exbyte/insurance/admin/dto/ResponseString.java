package com.exbyte.insurance.admin.dto;

import lombok.Getter;

@Getter
public enum ResponseString {
	
	STRING_LOGIN_SUCCESS("로그인 성공.", 200),
	STRING_EMAIL_SEND("이메일로 인증메일을 전송했습니다. 확인해주세요", 200),
	STRING_FAIL("작업에 실패했습니다.", 404),
	STRING_AUTH_FAIL("이메일 인증이 실패했습니다.", 200),
	STRING_AUTH_SUCCESS("이메일 인증이 완료되었습니다.", 200),
	STRING_NOT_HASH_PW("암호화되지 않은 비밀번호, 정상적인 방법으로 회원가입 하십시오.", 400),
	STRING_INVAILD_PW("비밀번호가 틀렸습니다.", 400);
	
	private final String message;
	private final int status;
	
	ResponseString(String message, int status){
		this.message = message;
		this.status = status;
	}
}
