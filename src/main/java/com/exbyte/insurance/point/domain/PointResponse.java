package com.exbyte.insurance.point.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointResponse {
	int pointNo;
	String pointName;
	String pointAdmin;
	
	public PointVO toEntity(String pointAdmin) {
		return PointVO.builder()
				.pointNo(pointNo)
				.pointName(pointName)
				.build();
	}
}
