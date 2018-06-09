package com.sunjoy.common.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.auth.dao.entity.Menu;
import com.sunjoy.common.auth.dao.mapper.MenuMapper;
import com.sunjoy.framework.dao.BaseDao;

@Repository
public class MenuDao extends BaseDao<MenuMapper,Menu>{

	@Override
	protected void setMapperClass() {
		super.setMapperClass(MenuMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Menu.class);
		
	}
	
	public List<Menu> getAllMenu(){
		return this.getMapper().getAllMenu();
	}
	
	public List<Menu> getMenusByUserId(Long userId){
		return this.getMapper().getMenusByUserId(userId);
	}
	
	public List<Menu> menuTree(){
		return this.getMapper().menuTree();
	}
	
	public List<Long> getMenusByRoleId(Long roleId){
		return this.getMapper().getMenusByRoleId(roleId);
	}
	
}
