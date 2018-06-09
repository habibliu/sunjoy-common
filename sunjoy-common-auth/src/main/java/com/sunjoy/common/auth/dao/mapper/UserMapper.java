package com.sunjoy.common.auth.dao.mapper;

import java.util.List;

import com.sunjoy.common.auth.dao.entity.Role;
import com.sunjoy.common.auth.dao.entity.User;

public interface UserMapper {
	/**
	 * 根据用户名取用户帐户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 根据用户ID获取其所属角色列表
	 * @param userId
	 * @return
	 */
	List<Role> getRolesByUserId(String userId);
}
