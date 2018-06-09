package com.sunjoy.common.auth.dao.entity;

import com.sunjoy.framework.dao.BaseEntity;

public class Role extends BaseEntity{
	
	private String name;
	private String nameZh;
	
	public String getName() {
		return name;
	}
	public String getNameZh() {
		return nameZh;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	
	
}
