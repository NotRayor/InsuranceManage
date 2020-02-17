package com.exbyte.insurance.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler extends RuntimeException {
	
	// 모든 예외 처리
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	// 기술침투는 하지 않는 것이 좋지만 뷰 페이지로 돌려야 하기 때문에 어쩔 수 없이 HttpServlet 객체를 사용 
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
		// 1. 로깅
		// 로그는 파일로 남겨야 하지만 지금은 jsp페이지로 보내기로 하자
		StringWriter error = new StringWriter();
		e.printStackTrace(new PrintWriter(error));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("/commons/error");
		
		return mav;
	}
}
