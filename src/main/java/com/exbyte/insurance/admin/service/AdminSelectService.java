package com.exbyte.insurance.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.PointDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.point.domain.PointVO;

@Service
public class AdminSelectService {
	
	AdminDAO adminDAO;

	@Inject
	public AdminSelectService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	public List<AdminVO> selectAdmin(AdminVO adminVO) throws Exception{
		return adminDAO.selectAdmin(adminVO);
	}
	
	public List<PointVO> selectAllPoint() throws Exception {
		return  adminDAO.selectAllPoint();
	}

	public List<AdminVO> selectAllAdmin() throws Exception {
		return adminDAO.selectAllAdmin();
	}
	
	public AdminVO selectAdminByEmail(String adminEmail) throws Exception {
		return adminDAO.selectAdminByEmail(adminEmail);
	}
	
	public List<PointDTO> selectPointAdmin() throws Exception {
		List<PointVO> list = adminDAO.selectAllPoint();
		List<PointDTO> listPoint = new ArrayList<>();
		for(PointVO pointVO : list) {
			PointDTO pointDTO = new PointDTO();
			try {
				AdminVO adminVO = adminDAO.selectPointAdmin(pointVO);
				pointDTO.setPointNo(pointVO.getPointNo());
				pointDTO.setPointName(pointVO.getPointName());
				pointDTO.setPointAdmin(adminVO.getAdminId());
				
			}catch (NullPointerException e) {
				e.printStackTrace();
				pointDTO.setPointAdmin("NULL");
			}
			listPoint.add(pointDTO);
		}
		
		return listPoint;
	}
	
}
