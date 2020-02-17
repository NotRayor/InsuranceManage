package com.exbyte.insurance.admin.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Position {
	
	EMPLOYEE("사원", true),
	MANAGER("지점장", true),
	OFFICER("관리자", false);
	
	private final String positionName;
	private final boolean hasLimit;
	
	Position(String positionName, boolean hasLimit){
		this.positionName = positionName;
		this.hasLimit = hasLimit;
	}
}
