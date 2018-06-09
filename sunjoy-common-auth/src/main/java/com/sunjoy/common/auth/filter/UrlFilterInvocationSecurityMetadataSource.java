package com.sunjoy.common.auth.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.sunjoy.common.auth.dao.entity.Menu;
import com.sunjoy.common.auth.dao.entity.Role;
import com.sunjoy.common.auth.service.IMenuService;

/**
 * 通过当前的请求地址，获取该地址需要的用户角色
 *
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private IMenuService menuService;

	AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 获取请求地址
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if ("/login".equals(requestUrl)) {
			return null;
		}
		//取出所有用户的菜单资源
		//TODO,此处频繁从数据库中取全部用户菜单资源，对性能有很大的影响，需要优化
		List<Menu> allMenu = menuService.getAllMenu();
		for (Menu menu : allMenu) {
			if (antPathMatcher.match(menu.getUrl(), requestUrl) && !menu.getRoles().isEmpty()) {
				List<Role> roles = menu.getRoles();
				int size = roles.size();
				String[] values = new String[size];
				for (int i = 0; i < size; i++) {
					values[i] = roles.get(i).getName();
				}
				return SecurityConfig.createList(values);
			}
		}
		// 没有匹配上的资源，都是登录访问
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
