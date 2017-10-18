package com.meession.market.common.util;

import org.apache.commons.beanutils.BeanUtils;

/**
 * A utility to clone object.Original object and target object has their own heap space.
 * It means that they are separated each other. 
 * @author fzh
 *
 */
public class BeanUtil {
	public static Object cloneObject(Object origObject){
		Object destObject = origObject.getClass().getInterfaces();
		try{
			BeanUtils.copyProperties(destObject, origObject);
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return destObject;
	}
}
