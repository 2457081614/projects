package com.meession.market.common.util;

import java.util.ResourceBundle;

public class ConfigUtils { 
    //config为包名，db为db.properties文件名省略properties后缀。
	private static final ResourceBundle dbConfig=ResourceBundle.getBundle("db");
	
	public static String getDBValue(String key){
		return dbConfig.getString(key);
	}	
}
