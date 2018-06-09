package com.sunjoy.common.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunjoy.common.auth.filter.AuthenticationAccessDeniedHandler;
import com.sunjoy.common.auth.filter.UrlAccessDecisionManager;
import com.sunjoy.common.auth.filter.UrlFilterInvocationSecurityMetadataSource;
import com.sunjoy.common.auth.service.ISecurityService;
import com.sunjoy.common.utils.JsonUtil;
import com.sunjoy.framework.client.dto.Response;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	ISecurityService securityService;
	@Autowired
	UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	@Autowired
	UrlAccessDecisionManager urlAccessDecisionManager;
	@Autowired
	AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html", "/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
				o.setAccessDecisionManager(urlAccessDecisionManager);
				return o;
			}
		}).and().formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("username")
				.passwordParameter("password").permitAll().failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, AuthenticationException e)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						StringBuilder sb = new StringBuilder();
						if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
							sb.append("用户名或密码输入错误，登录失败!");
						} else if (e instanceof DisabledException) {
							sb.append("账户被禁用，登录失败，请联系管理员!");
						} else {
							sb.append("登录失败!");
						}
						Response resp=new Response();
						resp.setCode("error");
						resp.setMessage(sb.toString());
						out.write(JsonUtil.toJson(resp));
						out.flush();
						out.close();
					}
				}).successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, Authentication authentication)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						Response resp=new Response();
						resp.setData(authentication.getPrincipal());
						resp.setMessage("登录成!");
						out.write(JsonUtil.toJson(resp));
						out.flush();
						out.close();
					}
				}).and().logout().permitAll().and().csrf().disable().exceptionHandling()
				.accessDeniedHandler(authenticationAccessDeniedHandler);
	}
}
