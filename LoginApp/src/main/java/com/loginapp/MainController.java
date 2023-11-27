package com.loginapp;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController 
{
	
	@GetMapping("/")
	public String viewwelcomepage(HttpServletRequest request)
	{	
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginpage()
	{
		return "login";
	}
	
	@GetMapping("/restricted")
	public String viewrestricted()
	{
		return "restricted";
	}
	
}
