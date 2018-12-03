package br.com.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(Model model)
	{		
		model.addAttribute("message", "Wellcome at Home Page");
		return "index";		
	}
	
	@RequestMapping("/login")
	public String login(Model model)
	{		
		return "login";		
	}
	
	@RequestMapping("/access-denied")
	public String accessDenied(Model model)
	{		
		return "access-denied";		
	}		
}