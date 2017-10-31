package org.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
			return "login";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() throws Exception {
		return "dashboard";
	}
	
}
