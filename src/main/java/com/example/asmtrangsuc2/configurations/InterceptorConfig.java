package com.example.asmtrangsuc2.configurations;

import com.example.asmtrangsuc2.interceptors.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	AuthenticationInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/admin/**", "/check-out", "/profile",
				"/order-history", "/order-detail/**", "/change-password", "/thankyou")
				.excludePathPatterns("/login");
	}

}
