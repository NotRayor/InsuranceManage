package com.exbyte.insurance.admin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminFindController {

	private final AdminService adminService;
	private final AdminMailService adminMailService;
	

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Inject
	public AdminFindController(AdminService adminService, AdminMailService adminMailService) {
		this.adminMailService = adminMailService;
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String findGET() {
		return "/admin/find";
	}
	
	@RequestMapping(value = "/findPOST", method = RequestMethod.POST)
	public String findPOST(@RequestParam("adminEmail") String adminEmail, Model model ,HttpServletRequest request) throws Exception {
		
		AdminVO adminVO = adminService.selectAdminByEmail(adminEmail);
		
		model.addAttribute("adminEmail", adminEmail);
		if(adminVO == null) {
			model.addAttribute("msg", "FAIL");
			return "/admin/find";
		}
		adminMailService.mailSendFindPw(adminVO, adminMailService.urlMaker(request));
		
		return "/admin/findResult";
	}
	
}
