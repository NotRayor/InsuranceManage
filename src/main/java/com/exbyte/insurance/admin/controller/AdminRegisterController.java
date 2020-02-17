package com.exbyte.insurance.admin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.dto.RegisterRequest;
import com.exbyte.insurance.admin.dto.ResponseString;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminRegisterService;
import com.exbyte.insurance.admin.service.AdminSelectService;
import com.exbyte.insurance.point.domain.PointVO;

@Controller
@RequestMapping("/admin")
public class AdminRegisterController {
	
	private AdminMailService adminMailService;
	private AdminRegisterService adminRegisterService;
	
	@Inject
	private AdminSelectService adminSelectService;
	
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminRegisterController(AdminRegisterService adminRegisterService, AdminMailService adminMailService) {
		this.adminMailService = adminMailService;
		this.adminRegisterService = adminRegisterService;
	}
	
	@RequestMapping(value = "/registerAgreement")
	public String registerSub() {
		return "/admin/registerSub";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(Model model) throws Exception {
		
		List<PointVO> points = adminSelectService.selectAllPoint();
		model.addAttribute("points", points);
		
		return;
	}
	
	@RequestMapping(value = "/registerPOST", method = RequestMethod.POST)
	public String registerPOST(RegisterRequest registerRequest, HttpServletRequest request ,RedirectAttributes redirectAttributes) throws Exception {
		try {
			AdminVO hashAdminVO = adminRegisterService.registerAccount(registerRequest);
			adminMailService.mailSend(hashAdminVO, adminMailService.urlMaker(request));
			redirectAttributes.addFlashAttribute("msg", ResponseString.STRING_EMAIL_SEND.getMessage());
		}catch (NullPointerException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", "NULL");
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", ResponseString.STRING_FAIL.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/admin/login";
	}
	

	
}
