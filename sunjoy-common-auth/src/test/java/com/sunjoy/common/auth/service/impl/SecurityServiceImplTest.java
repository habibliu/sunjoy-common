package com.sunjoy.common.auth.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityServiceImplTest {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testLoadUserByUsername() {
		fail("Not yet implemented");
	}

	@Test
	public void testPasswordEncode() {
		String password="123456";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        this.logger.info("password \"123456\" is encoded to {}",encode);
	}
}
