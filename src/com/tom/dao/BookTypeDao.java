package com.tom.dao; // DAO: Data Access Object 数据访问对象

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tom.model.BookType;
import com.tom.util.StringUtil;

/**
 * 数据库操作类
 * 
 * @author Administrator
 *
 */
public class BookTypeDao {
	/**
	 * 添加图书类别的方法
	 * 
	 * @throws Exception
	 */
	// 增加图书类别，conn的主要功能是连接数据库，bookType：图书类别的数据模型，即表示图书类别数据表
	public int add(Connection conn, BookType bookType) throws Exception {
		String sql = "insert into bookType values(null,?,?)"; // ?: 代表SQL命令的参数，严格区分顺序
		// String sql = "insert into bookType (bookTypeName, bookTypeDesc) values (?,
		// ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql); // 将sql语句与所连接的数据库绑定
		pstmt.setString(1, bookType.getBookTypeName()); // 为SQL语句的第1个参数(第1个问号)赋值
		pstmt.setString(2, bookType.getBookTypeDesc()); // 为SQL语句的第2个参数(第2个问号)赋值
		int a = pstmt.executeUpdate(); // 执行更新操作，广义的更新(增加，删除，修改)
		return a;
	}

	// 获取查询出图书类别的方法 返回参数是记录集(ResultSet)，其实就是一个数据表
	public ResultSet getBookTypeList(Connection conn, BookType bookType) throws Exception {
		StringBuffer sb = new StringBuffer("select * from bookType");
		if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" where bookTypeName like '%" + bookType.getBookTypeName() + "%'");
			// sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		// PreparedStatement pstmt=conn.prepareStatement(sb.toString().replace("and",
		// "where"));
		return pstmt.executeQuery(); // 执行查询操作，与select命令相关
	}

	// 更新 修改的操作
	public int update(Connection conn, BookType bookType) throws Exception {
		String sql = "update bookType set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}

	// 删除图书类型的操作
	public int delete(Connection conn, String id) throws Exception {
		String sql = "delete from bookType where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	public static ResultSet getBookCount(Connection con) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select booktypename,count(*) as num from booktype,book ");
		sb.append("where booktype.id=book.booktypeid ");
		sb.append("group by booktype.booktypename");
		PreparedStatement psmt = con.prepareStatement(sb.toString());
		return psmt.executeQuery();

	}

}
