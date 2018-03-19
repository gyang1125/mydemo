package com.bmw.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class <code>LoginController</code>
 * 
 * @author gyang
 *
 */
@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/")
	public String swaggerUi() {
		logger.debug("default login");
		return "redirect:/swagger-ui.html";
	}

	@GetMapping("/login")
	public String login() {
		logger.debug("login");
		return "redirect:/swagger-ui.html";

	}

}