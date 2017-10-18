package com.meession.education.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @author sam
 */
public abstract class ApplicationContextUtils {

	public static <T> T getBean(String beanName, Class<T> clazz) {
		ApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		return context.getBean(beanName, clazz);
	}

}
