package com.sunjoy.common.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunjoy.common.auth.dao.UserDao;
import com.sunjoy.common.auth.dao.entity.User;
import com.sunjoy.common.auth.service.ISecurityService;

@Service(value = "securityService")
@Transactional
public class SecurityServiceImpl implements ISecurityService{
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("用户不存在！");
		}
		//todo,补充用户角色
		return user;
	}
}
