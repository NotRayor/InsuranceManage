package com.exbyte.insurance.commons.interception;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.EmailAuthKey;
import com.exbyte.insurance.admin.service.AdminService;

public class CheckEmailInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(CheckEmailInterceptor.class);
	
	private AdminService adminService;
	
	@Inject
	public CheckEmailInterceptor(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String adminId = request.getParameter("adminId");
		String receiveAuthKey = request.getParameter("authKey");
		AdminVO adminVO = adminService.read(adminId);
		EmailAuthKey emailAuthKey = new EmailAuthKey(adminVO.getAdminAuthKey());
		
		
		if(emailAuthKey.getAuthKey().equals("Y")) {
			return true;
		}
		
		// 이메일 발송 시 key값과 다른 경우
		if(!emailAuthKey.confirmAuth(receiveAuthKey)) {
			logger.warn("AuthEmailInterceptor : 인증 키 거부");
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		logger.info("인증 키 인증 완료");
		adminVO.setAdminAuthKey(emailAuthKey.getAuthKey());
		adminService.updateAuthKey(adminVO);
		return true;
	}
	
	
}
