package com.meession.market.common.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author sam
 */
public abstract class PropertiesUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);

	private static Properties props;

	static {
		try {
			props = new Properties();
//			String env = EnvUtils.getEnv();
//			String location = "classpath:env/" + env + "/*.properties";
			String location = "classpath:env/dev/*.properties";
			Resource[] resources = new PathMatchingResourcePatternResolver()
					.getResources(location);

			for (Resource resource : resources) {
				PropertiesLoaderUtils.fillProperties(props, resource);
			}
		} catch (IOException e) {
			logger.error("Configuration files error", e);
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

}
