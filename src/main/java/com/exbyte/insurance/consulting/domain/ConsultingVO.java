package com.exbyte.insurance.consulting.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConsultingVO {
	int consultingNo;
	String adminId;
	String consultingName;
	String consultingPhone;
	String consultingJob;
	String consultingBirthday;
	String consultingCallTime;
	Boolean consultingIsCall;
	Boolean consultingIsEnd;
	Date consultingRegDate;
	String consultingFavoriteType;
	String consultingRegion;
	String consultingRemarks;
	
	@Builder
	public ConsultingVO(int consultingNo, String adminId, String consultingName, String consultingPhone,
			String consultingJob, String consultingBirthday, String consultingCallTime, Boolean consultingIsCall,
			Boolean consultingIsEnd, Date consultingRegDate, String consultingFavoriteType, String consultingRegion,
			String consultingRemarks) {
		this.consultingNo = consultingNo;
		this.adminId = adminId;
		this.consultingPhone = consultingPhone;
		this.consultingJob = consultingJob;
		this.consultingBirthday = consultingBirthday;
		this.consultingCallTime = consultingCallTime;
		this.consultingIsCall = consultingIsCall;
		this.consultingIsEnd = consultingIsEnd;
		this.consultingRegDate = consultingRegDate;
		this.consultingFavoriteType = consultingFavoriteType;
		this.consultingRegion = consultingRegion;
		this.consultingRemarks = consultingRemarks;
	}
}
