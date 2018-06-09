package com.sunjoy.common.auth.service;

import java.util.List;

import com.sunjoy.common.auth.dao.entity.Menu;

public interface IMenuService {
	/**
	 * 返回所有菜单
	 * @return
	 */
	List<Menu> getAllMenu();
}
