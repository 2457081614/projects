package com.meession.market;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback =false)
public abstract class AbstractSpringTest {

	private static ServletContextEvent sce;

	private static ServletContextListener listener;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockServletContext sc = new MockServletContext("");
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"classpath:spring/applicationContext.xml");
		listener = new ContextLoaderListener();
		sce = new ServletContextEvent(sc);
		listener.contextInitialized(sce);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		listener.contextDestroyed(sce);
	}

}
