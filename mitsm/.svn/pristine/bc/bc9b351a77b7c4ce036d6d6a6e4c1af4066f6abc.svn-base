package com.meession.market.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	private static String url;
	private static String username;
	private static String password;
	private static String dbDriver;

	/**
	 * 使用静态代码块进行初始化操作
	 */
	static {
		dbDriver = ConfigUtils.getDBValue("jdbc.driver");
		username = ConfigUtils.getDBValue("jdbc.username");
		password = ConfigUtils.getDBValue("jdbc.password");
		url = ConfigUtils.getDBValue("jdbc.url");
		try {
			Class.forName(dbDriver);// 加载数据库驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从数据源获取connection对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 关闭数据库资源
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
				st = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
