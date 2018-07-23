package com.sunjoy.common.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.debug("用户[{}]正在登录！",username);
		username="admin";
		User user=userDao.getUserByUsername(username);
		if(user==null) {
			logger.error("用户[{}]不存在!",user);
			throw new UsernameNotFoundException("用户["+username+"]不存在！");
		}
		logger.info("登录用户存在:{}",user);
		//todo,补充用户角色
		return user;
	}
}
