package com.tom.util;

import java.sql.Connection;
import java.sql.DriverManager;

// 定义连接数据库的工具类，有时会定义为静态类，不用每次使用时定义一个新对象
public class DbUtil {
	// 数据库连接的地址协议 子协议
	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
	// 用户名
	private String dbUserName = "root";
	// 密码
	private String dbPassword = "123456";
	// 驱动包
	private String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 连接数据库的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception {
		// 反射机制
		Class.forName(jdbcName); // 注册mysql驱动程序
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword); // 构造连接语句
		return con;
	}

	/**
	 * 关闭数据库的方法
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
