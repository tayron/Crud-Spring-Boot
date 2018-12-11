package br.com.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
	
	@RequestMapping("/authentication/login")
	public String login(Model model)
	{		
		return "authentication/login";		
	}
	
	@RequestMapping("/authentication/login-error")
	public String loginError(Model model)
	{		
		model.addAttribute("loginError", true);
		return "authentication/login";		
	}	
	
	@RequestMapping("/authentication/logout")
	public String logout(Model model)
	{		
		return "index";		
	}	
	
	@RequestMapping("/authentication/access-denied")
	public String accessDenied(Model model)
	{		
		return "authentication/access-denied";		
	}
}
