package com.exbyte.insurance.consulting.service;

import java.util.List;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.consulting.domain.ConsultingVO;

public interface ConsultingService {

	// 상담 등록
	void create(ConsultingVO consultingVO) throws Exception;
	// 특정 상담 읽기
	ConsultingVO read(int consultingNo) throws Exception;
	// 모든 상담 조회
	List<ConsultingVO> selectAll(Criteria criteria, AdminVO adminVO) throws Exception;
	// 상담 삭제 
	void delete(int consultingNo) throws Exception;
	// 상담 갯수 확인
	int countAll(Criteria criteria, AdminVO adminVO) throws Exception;
	// 상담 변경
	void update(ConsultingVO consultingVO) throws Exception;


}
