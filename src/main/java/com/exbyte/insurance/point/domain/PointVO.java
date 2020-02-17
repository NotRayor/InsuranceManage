package com.exbyte.insurance.point.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointVO {
	private int pointNo;
	private String pointName;
	
	@Builder
	public PointVO(int pointNo, String pointName) {
		this.pointNo = pointNo;
		this.pointName = pointName;
	}
}
