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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.dto.ResponseString;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminSelectService;

@Controller
@RequestMapping("/admin")
public class AdminEmailController {

	private AdminMailService adminMailService;
	
	@Inject
	private AdminSelectService adminSelectService;
	
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminEmailController(AdminMailService adminMailService) {
		this.adminMailService = adminMailService;
	}

	// CheckEmailInterceptor : preHandler 호출 - 이메일 키 인증
	@RequestMapping(value = "/confirm", method = RequestMethod.GET )
	public String confirmEmail(AdminVO adminVO, String authKey, Model model) throws Exception {

		model.addAttribute("msg", ResponseString.STRING_AUTH_SUCCESS.getMessage());
		
		return "/admin/login";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String emailSend(@RequestParam("adminEmail") String adminEmail, Model model) throws Exception {
		model.addAttribute("adminEmail", adminEmail);
		return "/admin/email";
	}
	
	
	// 이메일 재송신
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String emailResend(@RequestParam("adminEmail") String adminEmail, RedirectAttributes redirectAttribute,
			HttpServletRequest request) throws Exception {
		
		AdminVO adminVO;
		try {
			adminVO = adminSelectService.selectAdminByEmail(adminEmail);
			adminMailService.mailSend(adminVO, adminMailService.urlMaker(request));
			redirectAttribute.addFlashAttribute("msg", ResponseString.STRING_EMAIL_SEND.getMessage());
			return "redirect:/";
		}catch (NullPointerException e) {
			// TODO: handle exception
			redirectAttribute.addFlashAttribute("msg", ResponseString.STRING_FAIL.getMessage());
			return "redirect:/";
		}
	}
	
	
}
