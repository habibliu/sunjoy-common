package com.sunjoy.common.auth.dao;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.auth.dao.entity.User;
import com.sunjoy.common.auth.dao.mapper.UserMapper;
import com.sunjoy.framework.dao.BaseDao;

@Repository
public class UserDao extends BaseDao<UserMapper,User>{

	@Override
	protected void setMapperClass() {
		super.setMapperClass(UserMapper.class);
		
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(User.class);
		
	}
	
	public User getUserByUsername(String username) {
		return this.getMapper().getUserByUsername(username);
	}

}
