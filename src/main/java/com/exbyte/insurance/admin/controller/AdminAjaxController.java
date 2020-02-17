package com.exbyte.insurance.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.service.AdminService;
import com.exbyte.insurance.point.domain.PointVO;

@Controller
@RequestMapping("/admin")
public class AdminAjaxController {
	
	private final AdminService adminService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminAjaxController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// 지사 목록 전달
	@RequestMapping(value = "/listPoint", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PointVO>> requestPointListForAjax() throws Exception {
		ResponseEntity<List<PointVO>> entity = null;
		
		List<PointVO> list = adminService.selectAllPoint();
		try {
			for(PointVO pointVO : list) {
				entity = new ResponseEntity<>(list, HttpStatus.OK);
			}
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info(entity.toString());
		
		return entity;
	}

	
	// 회원정보 전달
	@RequestMapping(value = "/listAdmin", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AdminVO>> requestAdminListByPointForAjax(@RequestParam(value = "pointNo") String pointNo) throws Exception {
		int pointNoInt = Integer.parseInt(pointNo);
		ResponseEntity<List<AdminVO>> entity = null;
		// 이건 비즈니스 로직
		List<AdminVO> list = adminService.selectAllAdmin();
		List<AdminVO> listAdmin = new ArrayList<>();
		for(AdminVO adminVO : list) {
			if(adminVO.getAdminPoint() == pointNoInt) {
				listAdmin.add(adminVO);
			}
		}

		try {
			entity = new ResponseEntity<>(listAdmin, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public int requestDoubleCheckAdminId(@RequestParam("adminId") String adminId) throws Exception{
		
		// 1: 중복 , 0: 중복아님
		return adminService.countId(adminId);
		
	}

	
	// AJAX 값 반환용
	@RequestMapping(value = "/checkPosition", method = RequestMethod.GET)
	@ResponseBody
	public int requestDoubleCheckPosition(AdminVO adminVO) throws Exception{

		// 1: 거부 , 0: 승인
		return adminService.countPosition(adminVO);
		
	}

	// 이메일 중복 체크
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public int requestDoubleCheckEmail(@RequestParam("adminEmail") String adminEmail) throws Exception{
		
		// 1: 중복, 0: 중복아님
		return adminService.countEmail(adminEmail);
	}

	
	
	// 중복체크
	// 위의 일일이 체크하는 값들을 하나의 메소드로 해결할 수 있도록 구성한 것이다.
	@RequestMapping(value = "/duplicateCheck", method = RequestMethod.GET)
	public int requestDoubleCheck(AdminVO adminVO, @RequestParam("checkType") String checkType) throws Exception {
		
		return adminService.count(adminVO, checkType);
		
	}

	@RequestMapping(value = "/list/delete", method = RequestMethod.POST)
	@ResponseBody
	public int requestDeleteManyAdmin(Model model, @RequestParam(value="chkbox[]") List<String> list) throws Exception{
		
		for(String adminId : list) {
			adminService.delete(adminService.read(adminId));
		}
		
		return 1;
	}
	
}
