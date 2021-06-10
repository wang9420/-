package com.tom.dao;

/**
 *  快捷方式讲解：ctrl+shift+O  全部导入包
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tom.model.User;

public class UserDao {
	/**
	 * 登录验证
	 * 
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception {
		User resultUser = null;
		String sql = "select * from user where username=? and password=?";
		// 预处理
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getusername());
		pstmt.setString(2, user.getPassword());
		// 返回记录集
		ResultSet rs = pstmt.executeQuery();
		// 如何查询到记录 重新封装成一个user对象
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setusername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
}
