package com.exbyte.insurance.commons.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exbyte.insurance.admin.domain.AdminVO;

public class CheckRootAdminInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		AdminVO adminVO = (AdminVO)httpSession.getAttribute("login");
		
		
		if(!adminVO.getAdminPosition().equals("관리자")) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}

		
		return true;
	}
	
	
}
