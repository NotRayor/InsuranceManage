package com.exbyte.insurance.admin.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.dto.LoginDTO;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// 이메일을 통한 비밀번호 변경 페이지 이동
	@RequestMapping(value = "/updatePw", method = RequestMethod.GET)
	public String forwardUpdatePwByEmailMesssage(@RequestParam("adminId") String adminId, @RequestParam("authKey") String authKey,  Model model) throws Exception {

		// 잘못된 인증키 
		if(!authKey.equals(adminService.read(adminId).getAdminAuthKey())) {
			return "/admin/login";
		}

		model.addAttribute("adminId", adminId);
		model.addAttribute("authKey", authKey);
		
		return "/admin/updatePw";
	}
	
	// 이메일을 통한 비밀번호 변경 처리
	@RequestMapping(value = "/updatePw", method = RequestMethod.POST)
	public String requestUpdatePwByEmailMessage(LoginDTO loginDTO, Model model) throws Exception {
		AdminVO adminVO = adminService.read(loginDTO.getAdminId());
		
		// 암호화
		String hashPw = BCrypt.hashpw(loginDTO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		adminVO.setAdminAuthKey("Y");
		
		adminService.update(adminVO);
	
		return "/commons/index";
	}

	// 개인정보 페이지
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String forwardAccountPage(Model model, @RequestParam("adminId") String adminId) throws Exception {
		
		model.addAttribute("adminVO", adminService.read(adminId)); 
		
		return "/admin/account";
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String forwardDeleteAdminPage(@RequestParam(value="adminId") String adminId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		adminService.delete(adminService.read(adminId));
		HttpSession session = request.getSession();
		
		// 쿠키 및 세션 초기화 작업, 추후 Interception으로 일괄처리하도록 변경
		Object object = session.getAttribute("login");
		if(object != null) {
			session.removeAttribute("login");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		
		return "/commons/index";
	}

	// 회원 정보 변경
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String requestUpdateAdmin(Model model, AdminVO adminVO) throws Exception {
		
		AdminVO currentAdminVO = adminService.read(adminVO.getAdminId());
		currentAdminVO.setAdminEmail(adminVO.getAdminEmail());
		currentAdminVO.setAdminName(adminVO.getAdminName());
		
		model.addAttribute("adminId", adminVO.getAdminId());
		
		adminService.update(currentAdminVO);
		
		return "/admin/account";
	}
	
	
}



