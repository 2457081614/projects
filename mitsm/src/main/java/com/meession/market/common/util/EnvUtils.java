package com.meession.market.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Obtain the environment in which the current application is running.
 * 
 * @author sam
 */
public abstract class EnvUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(EnvUtils.class);

	public static final String ENV_PARAMETER = "spring.profiles.active";

	public static String getEnv() {
		String env = System.getProperty(ENV_PARAMETER);
		if (env == null) {
			env = System.getenv(ENV_PARAMETER);
		}
		logger.info(ENV_PARAMETER + ": " + env);
		return env;
	}

}
