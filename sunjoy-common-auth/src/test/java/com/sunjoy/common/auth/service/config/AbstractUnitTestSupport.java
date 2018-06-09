package com.sunjoy.common.auth.service.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.sunjoy.framework.client.core.ClientConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientConfig.class})
public abstract class AbstractUnitTestSupport {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());


  protected MockMvc mockMvc;

  @Autowired
  private GenericWebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    logger.debug(wac.getApplicationName());
   
  }
 
}