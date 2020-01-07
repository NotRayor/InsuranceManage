package com.exbyte.insurance.commons.interception;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exbyte.insurance.admin.service.AdminService;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private final String LOGIN = "login";
	
	@Inject
	private static AdminService adminService;
	
	// 컨테이너 URL 처리 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute(LOGIN) != null) {
			logger.info("clean Logger");
			httpSession.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	// URL 처리 후 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object adminVO = modelMap.get("admin");
		
		if(adminVO != null) {
			logger.info("LoginSuccess");
			httpSession.setAttribute(LOGIN, adminVO);
			
			if(request.getParameter("useCookie") != null) {
				logger.info("remember Cookie");
				//쿠키 생성
				Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*7); // 일주일 동안 보관

				// 쿠키 전송
				response.addCookie(loginCookie);
				
			} 
			
			response.sendRedirect("/");
			
		} else {
			logger.info("LoginFail in Interceptor ");
		}
	}
	
}
