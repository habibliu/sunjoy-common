package com.sunjoy.common.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.common.auth.dao.MenuDao;
import com.sunjoy.common.auth.dao.entity.Menu;
import com.sunjoy.common.auth.service.IMenuService;

@Service(value = "menuService")
@Transactional
public class MenuServiceImpl implements IMenuService{
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<Menu> getAllMenu() {
		return menuDao.getAllMenu();
	}

}
