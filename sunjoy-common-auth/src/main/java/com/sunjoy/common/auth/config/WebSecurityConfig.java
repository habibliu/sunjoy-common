package com.sunjoy.common.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunjoy.common.auth.filter.AuthenticationAccessDeniedHandler;
import com.sunjoy.common.auth.filter.UrlAccessDecisionManager;
import com.sunjoy.common.auth.filter.UrlFilterInvocationSecurityMetadataSource;
import com.sunjoy.common.auth.service.ISecurityService;
import com.sunjoy.framework.client.dto.Response;
import com.sunjoy.framework.utils.JsonUtil;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ISecurityService securityService;
	@Autowired
	UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	@Autowired
	UrlAccessDecisionManager urlAccessDecisionManager;
	@Autowired
	AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		/**
		 * 注入用户安全类
		 */
		auth.userDetailsService(securityService).passwordEncoder(
				new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html", "/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.withObjectPostProcessor(
						new ObjectPostProcessor<FilterSecurityInterceptor>() {
							@Override
							public <O extends FilterSecurityInterceptor> O postProcess(
									O o) {
								o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
								o.setAccessDecisionManager(urlAccessDecisionManager);
								return o;
							}
						})
				.antMatchers("/login","**","/**")////允许所有用户访问"/"
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/login")//指定登录页是"/login"
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(
							HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse,
							AuthenticationException e) throws IOException,
							ServletException {
						httpServletResponse
								.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						StringBuilder sb = new StringBuilder();
						if (e instanceof UsernameNotFoundException
								|| e instanceof BadCredentialsException) {
							sb.append("用户名或密码输入错误，登录失败!");
						} else if (e instanceof DisabledException) {
							sb.append("账户被禁用，登录失败，请联系管理员!");
						} else {
							sb.append("登录失败!");
						}
						Response resp = new Response();
						resp.setCode("error");
						resp.setMessage(sb.toString());
						out.write(JsonUtil.toJson(resp));
						out.flush();
						out.close();
					}
				})
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(
							HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse,
							Authentication authentication) throws IOException,
							ServletException {
						logger.debug("用户[{}]登录成功！",authentication.getPrincipal());
						httpServletResponse
								.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						Response resp = new Response();
						resp.setData(authentication.getPrincipal());
						resp.setMessage("登录成功!");
						out.write(JsonUtil.toJson(resp));
						out.flush();
						out.close();
					}
				}).and().logout().permitAll().and().cors().and().csrf().disable()
				.exceptionHandling()
				.accessDeniedHandler(authenticationAccessDeniedHandler);
		
		//解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
	}
}
