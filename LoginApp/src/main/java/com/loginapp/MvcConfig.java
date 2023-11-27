package com.loginapp;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@RestController
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver sesslr = new SessionLocaleResolver();
		sesslr.setDefaultLocale(Locale.US);
		
		return sesslr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor localChangei= new LocaleChangeInterceptor();
		localChangei.setParamName("lang");
		
		return localChangei;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(localeChangeInterceptor());
	}
}
