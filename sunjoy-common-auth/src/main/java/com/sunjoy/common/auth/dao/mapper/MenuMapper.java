package com.sunjoy.common.auth.dao.mapper;

import java.util.List;

import com.sunjoy.common.auth.dao.entity.Menu;


public interface MenuMapper {
	
	List<Menu> getAllMenu();

	List<Menu> getMenusByUserId(Long userId);

	List<Menu> menuTree();

	List<Long> getMenusByRoleId(Long roleId);
}
