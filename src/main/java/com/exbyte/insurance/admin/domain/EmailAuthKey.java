package com.exbyte.insurance.admin.domain;

import java.util.Random;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailAuthKey {
	
	private String authKey;
	private boolean permission;
	
	public EmailAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
	public static EmailAuthKey generateCode(int size) {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		
		do {
			num = ran.nextInt(75)+48;
			if((num >= 48 && num <=57) || (num >=65 && num <= 90) || (num >= 97 && num <=122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		} while (sb.length() < size);
		
		
		return new EmailAuthKey(sb.toString());
	}
	
	public Boolean confirmAuth(String str) {
		if(str.equals(authKey)) {
			authKey = "Y";
			return true;
		}
		else
			return false;
	}
}
