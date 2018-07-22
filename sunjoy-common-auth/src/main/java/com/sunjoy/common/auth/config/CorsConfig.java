package com.sunjoy.common.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author liuganchao<740033486@qq.com>
 * @date 2018年7月19日
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedHeaders("*")
				.allowCredentials(true)
				.allowedMethods("*")
				.maxAge(3600)
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials",
						"X-Frame-Options");
	}

}
