package com.meession.education;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appconfig-root.xml")
//@Transactional(transactionManager="transactionManager")  //添加了事务回滚，测试添加的数据最后执行完后数据会再次删掉，来保护数据库
public abstract class AbstractSpringTest {

	private static ServletContextEvent sce;

	private static ServletContextListener listener;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockServletContext sc = new MockServletContext("");
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"classpath:spring/appconfig-root.xml");
		listener = new ContextLoaderListener();
		sce = new ServletContextEvent(sc);
		listener.contextInitialized(sce);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		listener.contextDestroyed(sce);
	}

}
