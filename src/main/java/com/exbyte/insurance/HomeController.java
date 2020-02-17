package com.exbyte.insurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "commons/index";
	}

	@RequestMapping(value = "/intro")
	public String intro() {
		return "commons/intro";
	}
	
	@RequestMapping(value="/management")
	public String management() {
		return "commons/management";
	}
	
	
	
}
