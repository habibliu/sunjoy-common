package com.sunjoy.common.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedHeaders("X-Requested-With", "Content-Type", "Accept",
						"Authorization", "Origin", "Pragma",
						"Access-Control-Allow-Headers","Cache-Control","FrontType")
				.allowedMethods("*").maxAge(3600);
	}

}
